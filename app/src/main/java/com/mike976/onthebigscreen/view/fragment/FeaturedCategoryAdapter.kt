package com.mike976.onthebigscreen.view.fragment

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onthebigscreen.R
import com.example.onthebigscreen.featured.model.Media
import com.example.onthebigscreen.featured.model.Movie
import com.mike976.onthebigscreen.extensions.inflate
import com.mike976.onthebigscreen.util.getProgressDrawable
import com.mike976.onthebigscreen.util.loadImage
import kotlinx.android.synthetic.main.list_item_media.view.*

class FeaturedCategoryAdapter(val moviesByCategory: MutableList<Media>) : RecyclerView.Adapter<FeaturedCategoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //progressDrawable is a spinner that runs in imageview to provide visual feedback to user the image is loading
        private val progressDrawable = getProgressDrawable(itemView.context)

        fun bind(media: Media?) {
            if(media != null) {
                itemView.mediaImage.loadImage(media.posterUrl, progressDrawable)
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