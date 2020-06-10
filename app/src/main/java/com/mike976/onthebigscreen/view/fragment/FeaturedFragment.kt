package com.mike976.onthebigscreen.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onthebigscreen.R
import com.example.onthebigscreen.featured.model.Movie
import com.example.onthebigscreen.featured.model.TvShow
import com.example.onthebigscreen.network.ApiError
import com.example.onthebigscreen.network.ApiResponseMessage
import com.example.onthebigscreen.network.ApiResponseStatus
import com.mike976.onthebigscreen.model.MoviesListType
import com.mike976.onthebigscreen.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_featured.*

class FeaturedFragment : Fragment() {

    //TODO Refactor

    private val featuredAdapter = FeaturedAdapter(mutableListOf())

    companion object {
        fun newInstance() =
            FeaturedFragment()
    }

    private lateinit var viewModel: MainViewModel

    private val mediaListFragment =
        MediaListFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_featured, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Note the app has one activity - MainActivity - so we pull out the MainViewModel.
        //To share the same instance of MainViewModel across fragments, we're using the mainactivity to provide us the viewmodel
        viewModel = activity?.let { ViewModelProviders.of(it).get(MainViewModel::class.java) }!!

        //println(viewModel.counter) //same instance viewmodel checker

        featuredRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        featuredRecyclerView.adapter = featuredAdapter

        featuredAdapter.updateCategories(listOf("Now Playing", "Upcoming", "Trending Movies", "Trending TV"))

        viewModel.getMovies(MoviesListType.NowPlayingMovies)?.observe(this, Observer<ApiResponseMessage<List<Movie>>> { responseMessage ->

            if(responseMessage?.statusApi == ApiResponseStatus.SUCCESS && responseMessage.data != null) {
                val movies = responseMessage.data

                featuredAdapter.updateNowPlayingMovies(movies)

            } else {
                if (responseMessage?.error == ApiError.NOW_PLAYING_MOVIES) {
                    Toast.makeText(context, "Now Playing movies not found", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.getMovies(MoviesListType.UpComingMovies)?.observe(this, Observer<ApiResponseMessage<List<Movie>>> { responseMessage ->

            if(responseMessage?.statusApi == ApiResponseStatus.SUCCESS && responseMessage.data != null) {
                val movies = responseMessage.data

                featuredAdapter.updateUpComingMovies(movies)

            } else {
                if (responseMessage?.error == ApiError.UPCOMING_MOVIES) {
                    Toast.makeText(context, "Upcoming movies not found", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.getMovies(MoviesListType.TrendingMovies)?.observe(this, Observer<ApiResponseMessage<List<Movie>>> { responseMessage ->

            if(responseMessage?.statusApi == ApiResponseStatus.SUCCESS && responseMessage.data != null) {
                val movies = responseMessage.data

                featuredAdapter.updateTrendingMovies(movies)

            } else {
                if (responseMessage?.error == ApiError.TRENDING_MOVIES) {
                    Toast.makeText(context, "Trending movies not found", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.getTrendingTvShows()?.observe(this, Observer<ApiResponseMessage<List<TvShow>>> { responseMessage ->

            if(responseMessage?.statusApi == ApiResponseStatus.SUCCESS && responseMessage.data != null) {
                val tvShows = responseMessage.data

                featuredAdapter.updateTrendingTvShows(tvShows)

            } else {
                if (responseMessage?.error == ApiError.TRENDING_TVSHOWS) {
                    Toast.makeText(context, "Trending Tv Shows not found", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun switchToFragment(fragment: Fragment) {

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(fragment.javaClass.name)
            .commit()

    }



}