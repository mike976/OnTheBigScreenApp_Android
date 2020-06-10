package com.mike976.onthebigscreen.view.fragment

import android.media.tv.TvInputService
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.onthebigscreen.R
import com.example.onthebigscreen.featured.model.Media
import com.example.onthebigscreen.featured.model.Movie
import com.example.onthebigscreen.featured.model.TvShow
import com.mike976.onthebigscreen.extensions.inflate
import kotlinx.android.synthetic.main.list_item_featured_media.view.*

class FeaturedAdapter(
    val featuredCategories: MutableList<String>) : RecyclerView.Adapter<FeaturedAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()
    private val featuredNowPlayingMovies = mutableListOf<Movie>()
    private val featuredUpComing = mutableListOf<Movie>()
    private val featuredTrendingMovies = mutableListOf<Movie>()
    private val featuredTrendingTvShows= mutableListOf<TvShow>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val featuredCategoryAdapter = FeaturedCategoryAdapter(mutableListOf())


        init {
            itemView.setOnClickListener(this)

            itemView.featuredMediaRecyclerView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false )
            itemView.featuredMediaRecyclerView.adapter = featuredCategoryAdapter

        }

        override fun onClick(v: View) {

        }

        fun bindFeaturedCategory(category: String) {
            itemView.featuredHeaderButton.text = category
        }

        fun updateFeaturedMedia(movies: List<Media>) {
            featuredCategoryAdapter.updateMoviesForCategory(movies)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(parent.inflate(R.layout.list_item_featured_media))

        viewHolder.itemView.featuredMediaRecyclerView.setRecycledViewPool(this.viewPool)

        LinearSnapHelper().attachToRecyclerView(viewHolder.itemView.featuredMediaRecyclerView)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return featuredCategories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val category = featuredCategories[position]
        holder.bindFeaturedCategory(category)

        when (category) {
            "Now Playing" -> holder.updateFeaturedMedia(this.featuredNowPlayingMovies)
            "Upcoming" -> holder.updateFeaturedMedia(this.featuredUpComing)
            "Trending Movies" -> holder.updateFeaturedMedia(this.featuredTrendingMovies)
            "Trending TV" -> holder.updateFeaturedMedia(this.featuredTrendingTvShows)
        }
    }

    fun updateCategories(categories: List<String>) {
        this.featuredCategories.clear()
        this.featuredCategories.addAll(categories)
        notifyDataSetChanged()
    }

    fun updateNowPlayingMovies(movies: List<Movie>) {
        this.featuredNowPlayingMovies.clear()
        this.featuredNowPlayingMovies.addAll(movies)
        notifyDataSetChanged()
    }

    fun updateUpComingMovies(movies: List<Movie>) {
        this.featuredUpComing.clear()
        this.featuredUpComing.addAll(movies)
        notifyDataSetChanged()
    }

    fun updateTrendingMovies(movies: List<Movie>) {
        this.featuredTrendingMovies.clear()
        this.featuredTrendingMovies.addAll(movies)
        notifyDataSetChanged()
    }

    fun updateTrendingTvShows(tvShows: List<TvShow>) {
        this.featuredTrendingTvShows.clear()
        this.featuredTrendingTvShows.addAll(tvShows)
        notifyDataSetChanged()
    }

}