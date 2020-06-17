package com.mike976.onthebigscreen.view.adapter.featured

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onthebigscreen.R
import com.mike976.onthebigscreen.model.Media
import com.mike976.onthebigscreen.model.Movie
import com.mike976.onthebigscreen.model.TvShow
import com.mike976.onthebigscreen.extensions.inflate
import com.mike976.onthebigscreen.model.FeaturedCategory
import com.mike976.onthebigscreen.view.BaseViewHolder
import com.mike976.onthebigscreen.view.fragment.featured.FeaturedMediaCategoryFragment
import com.mike976.onthebigscreen.view.fragment.featured.FeaturedMediaFragment
import kotlinx.android.synthetic.main.list_item_featured_media.view.*


class FeaturedMediaAdapter(
    val featuredCategories: MutableList<FeaturedCategory>) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val viewPool = RecyclerView.RecycledViewPool()
    private val featuredNowPlayingMovies = mutableListOf<Movie>()
    private val featuredUpComing = mutableListOf<Movie>()
    private val featuredTrendingMovies = mutableListOf<Movie>()
    private val featuredTrendingTvShows= mutableListOf<TvShow>()

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEMS = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {

        when (viewType){
            TYPE_HEADER -> {
                val viewHolder = FeaturedHeaderViewHolder(parent.inflate(R.layout.list_item_featured_media_header))
                return viewHolder
            }
            TYPE_ITEMS -> {
                val viewHolder = FeaturedItemViewHolder(parent.inflate(R.layout.list_item_featured_media))
                viewHolder.itemView.featuredMediaRecyclerView.setRecycledViewPool(this.viewPool)
                return viewHolder
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }


        //LinearSnapHelper().attachToRecyclerView(viewHolder.itemView.featuredMediaRecyclerView)

        //return viewHolder
    }

    override fun getItemCount(): Int {
        return featuredCategories.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER
        }

        return TYPE_ITEMS
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {

        //For FeaturedDetail View
        if(holder is FeaturedItemViewHolder) {
            val category = featuredCategories[position - 1]
            holder.bindFeaturedCategory(category)

            when (category) {
                FeaturedCategory.NowPlaying -> holder.updateFeaturedMedia(this.featuredNowPlayingMovies)
                FeaturedCategory.Upcoming -> holder.updateFeaturedMedia(this.featuredUpComing)
                FeaturedCategory.TrendingMovies -> holder.updateFeaturedMedia(this.featuredTrendingMovies)
                FeaturedCategory.TrendingTv -> holder.updateFeaturedMedia(this.featuredTrendingTvShows)
                FeaturedCategory.Unknown -> {}
            }
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

    inner class FeaturedItemViewHolder(itemView: View) : BaseViewHolder<View>(itemView) {

        private val featuredCategoryAdapter =
            FeaturedMediaCategoryAdapter(
                mutableListOf()
            )

        private var featuredCategory = FeaturedCategory.Unknown

        init {

            itemView.featuredHeaderTextView.setOnClickListener {

                val activity = itemView.context as AppCompatActivity
                activity.supportFragmentManager.beginTransaction().replace(R.id.featuredMediaContainer,
                    FeaturedMediaCategoryFragment(
                        this.featuredCategory
                    )
                )
                    .addToBackStack(FeaturedMediaFragment::class.java.name)
                    .commit()

            }

            itemView.featuredMediaRecyclerView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false )
            itemView.featuredMediaRecyclerView.adapter = featuredCategoryAdapter

        }

        fun bindFeaturedCategory(category: FeaturedCategory) {
            this.featuredCategory = category
            itemView.featuredHeaderTextView.text =  when(category) {
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

        override fun bind(item: View) {

        }

    }

    inner class FeaturedHeaderViewHolder(itemView: View): BaseViewHolder<View>(itemView) {

        override fun bind(item: View) {

        }

    }
}