package com.mike976.onthebigscreen.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.example.onthebigscreen.R
import com.example.onthebigscreen.featured.model.Media
import com.mike976.onthebigscreen.model.FeaturedCategory
import com.mike976.onthebigscreen.util.ItemOffsetDecoration
import com.mike976.onthebigscreen.view.adapter.FeaturedMediaPagedCategoryAdapter
import com.mike976.onthebigscreen.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_featured_media_category.*
import java.lang.Exception

class FeaturedMediaCategoryFragment(val featuredCategory: FeaturedCategory) : Fragment() {

    companion object {
        fun newInstance() =
            FeaturedMediaCategoryFragment(FeaturedCategory.Unknown)
    }

    private lateinit var viewModel: MainViewModel

    private val adapter = FeaturedMediaPagedCategoryAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_featured_media_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.let { ViewModelProviders.of(it).get(MainViewModel::class.java) }!!

        shouldShowActionBar(true)

        featuredCategoryRecyclerView.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        featuredCategoryRecyclerView.adapter = adapter
        featuredCategoryRecyclerView.addItemDecoration(
            ItemOffsetDecoration(
                5
            )
        );

        if(featuredCategory == FeaturedCategory.Unknown) {
            return
        }

        var pagedList = getPagedListByCategory(featuredCategory)
        pagedList.observe(this, Observer {

            adapter.submitList(it)
        })
    }

    override fun onDestroyView() {
        shouldShowActionBar(false)
        super.onDestroyView()
    }

    private fun getPagedListByCategory(featuredCategory: FeaturedCategory): LiveData<PagedList<Media>> {
        return when(featuredCategory) {
            FeaturedCategory.TrendingTv -> viewModel.trendingTvShowsPagedList
            FeaturedCategory.NowPlaying -> viewModel.nowPlayingMoviesPagedList
            FeaturedCategory.Upcoming -> viewModel.upComingMoviesPagedList
            FeaturedCategory.TrendingMovies -> viewModel.trendingMoviesPagedList

            else -> viewModel.nowPlayingMoviesPagedList
        }
    }

    fun shouldShowActionBar(show: Boolean) {
        try {
            val activity = view?.context as AppCompatActivity
            activity?.supportActionBar?.title = featuredCategory.description

            if(show) {
                activity?.supportActionBar!!.show()
            } else {
                activity?.supportActionBar!!.hide()
            }


        } catch (e: Exception) {
        }
    }
}

