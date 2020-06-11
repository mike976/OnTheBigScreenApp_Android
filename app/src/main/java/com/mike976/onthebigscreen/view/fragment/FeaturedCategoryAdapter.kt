package com.mike976.onthebigscreen.view.fragment

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.onthebigscreen.R
import com.example.onthebigscreen.featured.model.Media
import com.example.onthebigscreen.featured.model.MediaType
import com.example.onthebigscreen.featured.model.Movie
import com.example.onthebigscreen.featured.model.TvShow
import com.mike976.onthebigscreen.extensions.inflate
import com.mike976.onthebigscreen.util.getProgressDrawable
import com.mike976.onthebigscreen.util.loadImage
import kotlinx.android.synthetic.main.list_item_media.view.*

class FeaturedCategoryAdapter(val moviesByCategory: MutableList<Media>) : RecyclerView.Adapter<FeaturedCategoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        //progressDrawable is a spinner that runs in imageview to provide visual feedback to user the image is loading
        private val progressDrawable = getProgressDrawable(itemView.context)

        private lateinit var media: Media

        init {
            itemView.mediaImage.setOnClickListener(this)
        }

        fun bind(media: Media?) {
            if(media != null) {
                this.media = media
                itemView.mediaImage.loadImage(media.posterUrl, progressDrawable)

            }
        }

        override fun onClick(v: View?) {
            if (this.media != null) {

                var mediaType = MediaType.None
                if (media is Movie) {
                    mediaType = MediaType.Movie
                }  else if (media is TvShow) {
                    mediaType = MediaType.TVShow
                }

                val activity = itemView.context as AppCompatActivity
                activity.supportFragmentManager.beginTransaction().replace(R.id.myContainer, MediaDetailFragment(media.id, mediaType))
                    .addToBackStack(FeaturedFragment.javaClass.name)
                    .commit()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_media))
    }

    override fun getItemCount(): Int {
        return moviesByCategory.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(moviesByCategory[position])
    }

    fun updateMoviesForCategory(movies: List<Media>) {
        this.moviesByCategory.clear()
        this.moviesByCategory.addAll(movies)
        notifyDataSetChanged()
    }
}