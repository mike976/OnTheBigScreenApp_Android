package com.mike976.onthebigscreen.view.fragment.media

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onthebigscreen.R
import com.example.onthebigscreen.featured.model.Media
import com.example.onthebigscreen.featured.model.MediaType
import com.example.onthebigscreen.network.ApiError
import com.example.onthebigscreen.network.ApiResponseStatus
import com.mike976.onthebigscreen.util.applyRoundedCorners
import com.mike976.onthebigscreen.util.getProgressDrawable
import com.mike976.onthebigscreen.util.loadImage
import com.mike976.onthebigscreen.view.adapter.media.MediaCastAdapter
import com.mike976.onthebigscreen.view.adapter.media.MediaCrewAdapter
import com.mike976.onthebigscreen.view.adapter.media.MediaProductionAdapter
import com.mike976.onthebigscreen.view.adapter.media.MediaTrailerAdapter
import com.mike976.onthebigscreen.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_media_detail.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MediaDetailFragment(private val media: Media) : Fragment() {

    private val trailerAdapter = MediaTrailerAdapter(mutableListOf())
    private val productionCompanyAdapter = MediaProductionAdapter(mutableListOf())
    private val mediaCastAdapter = MediaCastAdapter(mutableListOf())
    private val mediaCrewAdapter = MediaCrewAdapter(mutableListOf())

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_media_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.let { ViewModelProviders.of(it).get(MainViewModel::class.java) }!!

        buildRecyclerViews()

        loadMedia()
        loadMediaDetail()
        loadMediaCredits()

    }

    private fun buildRecyclerViews() {

        list_trailers.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        list_trailers.adapter = trailerAdapter

        val includedLayout = view?.findViewById<View>(R.id.included_cast_crew_production_layout)
        val productionRecyclerView =
            includedLayout?.findViewById<View>(R.id.production_list) as RecyclerView
        val castRecyclerView = includedLayout?.findViewById<View>(R.id.cast_list) as RecyclerView
        val crewRecyclerView = includedLayout?.findViewById<View>(R.id.crew_list) as RecyclerView

        if (productionRecyclerView != null) {
            productionRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            productionRecyclerView.adapter = productionCompanyAdapter
        }

        if (castRecyclerView != null) {
            castRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            castRecyclerView.adapter = mediaCastAdapter
        }

        if (crewRecyclerView != null) {
            crewRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            crewRecyclerView.adapter = mediaCrewAdapter
        }
    }


    private fun loadMedia() {

        //backdrop & poster images
        activity?.let {
            val progressDrawable = getProgressDrawable(it)
            if(media.backdropPath != null) {
                image_banner.loadImage(media.backdropUrl, progressDrawable)
            }

            if(media.posterPath != null) {
                poster_banner.loadImage(media.posterUrl, progressDrawable)
            }
        }

        backdropTitleTextView.text = this.media.title
        title.text = this.media.title
        overview.text = this.media.overview

        //release date
        var releaseDate: LocalDate? = null

        if(this.media.mediaType == MediaType.Movie) {
            releaseDate = LocalDate.parse(this.media.releaseDate)
        } else if(this.media.mediaType == MediaType.TVShow) {
            releaseDate = LocalDate.parse(this.media.firstAirDate)
        }

        releaseDate?.format(DateTimeFormatter.ofPattern("yyyy"))
        releaseYear.text = releaseDate?.year.toString()

        //apply ratings
        ratingsBar.rating = (this.media.voteAverage * 5 / 10)

        //media type
        var fillColor: String? = null
        if(media.mediaType == MediaType.TVShow) {
            mediaDetailType.text = this.getString(R.string.media_type_tv)
            fillColor = "#ff8f00"
        } else if(media.mediaType == MediaType.Movie) {
            mediaDetailType.text = this.getString(R.string.media_type_movie)
            fillColor = "#76ff03"
        }

        mediaDetailType.applyRoundedCorners(fillColor)


    }

    private fun loadMediaDetail() {

        viewModel.getMediaDetail(this.media.id, this.media.mediaType)?.observe(this, Observer {responseMessage ->

            if(responseMessage?.statusApi == ApiResponseStatus.SUCCESS && responseMessage.data != null) {
                val data = responseMessage.data

                val first = data.appendedVideos.results?.firstOrNull()
                if(first != null) {
                    trailerAdapter.updateTrailers(mutableListOf(first))
                }

                productionCompanyAdapter.updateProductionCompanies(data.productionCompanies)

            } else {
                if (responseMessage?.error == ApiError.MOVIEDETAIL) {
                    Toast.makeText(context, "Media Detail not found", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun loadMediaCredits () {
        viewModel.getMediaCredits(this.media.id, this.media.mediaType)
            ?.observe(this, Observer { responseMessage ->

                if (responseMessage?.statusApi == ApiResponseStatus.SUCCESS && responseMessage.data != null) {
                    val data = responseMessage.data

                    mediaCastAdapter.updateCastList(data.cast)
                    mediaCrewAdapter.updateCrewList(data.crew)


                } else {
                    if (responseMessage?.error == ApiError.MOVIECREDITS) {
                        Toast.makeText(context, "Media credits not found", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
    }
}