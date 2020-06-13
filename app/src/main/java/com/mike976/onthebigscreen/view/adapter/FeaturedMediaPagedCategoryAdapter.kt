package com.mike976.onthebigscreen.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.onthebigscreen.R
import com.example.onthebigscreen.featured.model.Media
import com.example.onthebigscreen.featured.model.MediaType
import com.example.onthebigscreen.featured.model.Movie
import com.example.onthebigscreen.featured.model.TvShow
import com.mike976.onthebigscreen.extensions.inflate
import com.mike976.onthebigscreen.util.getProgressDrawable
import com.mike976.onthebigscreen.util.loadImage
import com.mike976.onthebigscreen.view.fragment.FeaturedMediaFragment
import com.mike976.onthebigscreen.view.fragment.MediaDetailFragment
import kotlinx.android.synthetic.main.list_item_media.view.*

class FeaturedMediaPagedCategoryAdapter(val mediaByCategory: MutableList<Media>) : PagedListAdapter<Media, FeaturedMediaPagedCategoryAdapter.ViewHolder>(MediaDiffUtilCallback()) {


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

                if(media.posterPath != null) {
                    itemView.mediaImage.loadImage(media.posterUrl, progressDrawable)
                }
            }
        }

        override fun onClick(v: View?) {
            if (this.media != null) {
                navigateToMediaDetailFragment(this.media)
            }
        }

        private fun navigateToMediaDetailFragment(media: Media) {

            var mediaType = MediaType.None
            if (media is Movie) {
                mediaType = MediaType.Movie
            }  else if (media is TvShow) {
                mediaType = MediaType.TVShow
            }

            val activity = itemView.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().replace(R.id.featuredMediaContainer,
                MediaDetailFragment(
                    media.id,
                    mediaType
                )
            )
                .addToBackStack(FeaturedMediaFragment.javaClass.name)
                .commit()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_media))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val item = getItem(position)
        holder.bind(item)
    }

}

class MediaDiffUtilCallback : DiffUtil.ItemCallback<Media>() {


    override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
        //return oldItem?.id == newItem?.id
        return oldItem.equals(newItem)
    }
}