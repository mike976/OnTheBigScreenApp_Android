package com.mike976.onthebigscreen.view.adapter.featured

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.onthebigscreen.R
import com.mike976.onthebigscreen.model.Media
import com.mike976.onthebigscreen.extensions.inflate
import com.mike976.onthebigscreen.util.getProgressDrawable
import com.mike976.onthebigscreen.util.loadImage
import com.mike976.onthebigscreen.view.fragment.featured.FeaturedMediaFragment
import com.mike976.onthebigscreen.view.fragment.media.MediaDetailFragment
import kotlinx.android.synthetic.main.list_item_media.view.*

class FeaturedMediaCategoryAdapter(val mediaByCategory: MutableList<Media>) : RecyclerView.Adapter<FeaturedMediaCategoryAdapter.ViewHolder>() {

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
            navigateToMediaDetailFragment(this.media)
        }

        private fun navigateToMediaDetailFragment(media: Media) {

            val activity = itemView.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().replace(R.id.featuredMediaContainer,
                MediaDetailFragment(media)
            )
                .addToBackStack(FeaturedMediaFragment::class.java.name)
                .commit()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_media))
    }

    override fun getItemCount(): Int {
        return mediaByCategory.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mediaByCategory[position])
    }

    fun updateMoviesForCategory(movies: List<Media>) {
        this.mediaByCategory.clear()
        this.mediaByCategory.addAll(movies)
        notifyDataSetChanged()
    }
}