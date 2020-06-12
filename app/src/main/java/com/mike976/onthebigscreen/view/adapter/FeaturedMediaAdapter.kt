package com.mike976.onthebigscreen.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.onthebigscreen.R
import com.example.onthebigscreen.featured.model.Media
import com.example.onthebigscreen.featured.model.Movie
import com.example.onthebigscreen.featured.model.TvShow
import com.mike976.onthebigscreen.extensions.inflate
import com.mike976.onthebigscreen.model.FeaturedCategory
import com.mike976.onthebigscreen.view.fragment.FeaturedMediaCategoryFragment
import com.mike976.onthebigscreen.view.fragment.FeaturedMediaFragment
import kotlinx.android.synthetic.main.list_item_featured_media.view.*


class FeaturedMediaAdapter(
    val featuredCategories: MutableList<FeaturedCategory>) : RecyclerView.Adapter<FeaturedMediaAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()
    private val featuredNowPlayingMovies = mutableListOf<Movie>()
    private val featuredUpComing = mutableListOf<Movie>()
    private val featuredTrendingMovies = mutableListOf<Movie>()
    private val featuredTrendingTvShows= mutableListOf<TvShow>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val featuredCategoryAdapter =
            FeaturedMediaCategoryAdapter(
                mutableListOf()
            )

        private var featuredCategory = FeaturedCategory.Unknown

        init {

            itemView.featuredHeaderButton.setOnClickListener {

                val activity = itemView.context as AppCompatActivity
                activity.supportFragmentManager.beginTransaction().replace(R.id.featuredMediaContainer,
                    FeaturedMediaCategoryFragment(
                        this.featuredCategory
                    )
                )
                    .addToBackStack(FeaturedMediaFragment.javaClass.name)
                    .commit()

            }

            itemView.featuredMediaRecyclerView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false )
            itemView.featuredMediaRecyclerView.adapter = featuredCategoryAdapter

        }

        fun bindFeaturedCategory(category: FeaturedCategory) {
            this.featuredCategory = category
            itemView.featuredHeaderButton.text =  when(category) {
                FeaturedCategory.NowPlaying -> itemView.context.getString(R.string.featured_category_nowplaying)
                FeaturedCategory.Upcoming ->  itemView.context.getString(R.string.featured_category_upcoming)
                FeaturedCategory.TrendingMovies ->  itemView.context.getString(R.string.featured_category_trendingmovies)
                FeaturedCategory.TrendingTv ->  itemView.context.getString(R.string.featured_category_trending_tvshows)
                else ->  "Error"
            }
        }

        fun updateFeaturedMedia(mediaList: List<Media>) {
            featuredCategoryAdapter.updateMoviesForCategory(mediaList)
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
            FeaturedCategory.NowPlaying -> holder.updateFeaturedMedia(this.featuredNowPlayingMovies)
            FeaturedCategory.Upcoming -> holder.updateFeaturedMedia(this.featuredUpComing)
            FeaturedCategory.TrendingMovies -> holder.updateFeaturedMedia(this.featuredTrendingMovies)
            FeaturedCategory.TrendingTv -> holder.updateFeaturedMedia(this.featuredTrendingTvShows)
        }
    }

    fun updateCategories(categories: List<FeaturedCategory>) {
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