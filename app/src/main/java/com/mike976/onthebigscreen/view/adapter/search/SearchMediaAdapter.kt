package com.mike976.onthebigscreen.view.adapter.search

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.onthebigscreen.R
import com.mike976.onthebigscreen.model.Media
import com.mike976.onthebigscreen.model.MediaType
import com.mike976.onthebigscreen.extensions.inflate
import com.mike976.onthebigscreen.util.applyRoundedCorners
import com.mike976.onthebigscreen.util.getProgressDrawable
import com.mike976.onthebigscreen.util.loadImage
import com.mike976.onthebigscreen.view.fragment.media.MediaDetailFragment
import com.mike976.onthebigscreen.view.fragment.search.SearchMediaFragment
import kotlinx.android.synthetic.main.list_item_media_searchresult.view.*


class SearchMediaAdapter(private val searchResults: MutableList<Media>) : RecyclerView.Adapter<SearchMediaAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val progressDrawable = getProgressDrawable(itemView.context)

        private lateinit var media: Media

        fun bind(media: Media) {
            this.media = media
            itemView.titleTextView.text = media.title
            itemView.releaseYearTextView.text = "Year: ${media.formattedReleaseYear}"

            itemView.setOnClickListener{
                navigateToMediaDetailFragment(this.media)
            }

            //apply poster
            itemView.posterImage.loadImage(media.posterUrl, progressDrawable)

            //apply ratings
            itemView.mediaRatingsBar.rating = (media.voteAverage * 5 / 10)

            var fillColor: String? = null
            if(media.mediaType == MediaType.TVShow) {
                itemView.mediaTypeTextView.text = itemView.context.getString(R.string.media_type_tv)
                fillColor = "#ff8f00"
            } else if(media.mediaType == MediaType.Movie) {
                itemView.mediaTypeTextView.text = itemView.context.getString(R.string.media_type_movie)
                fillColor = "#76ff03"
            }

            itemView.mediaTypeTextView.applyRoundedCorners(fillColor)
        }

        private fun navigateToMediaDetailFragment(media: Media) {

            val activity = itemView.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().replace(R.id.searchMediaContainer,
                MediaDetailFragment(media)
            )
                .addToBackStack(SearchMediaFragment::class.java.name)
                .commit()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_media_searchresult, parent))
        return ViewHolder(parent.inflate(R.layout.list_item_media_searchresult))
    }

    override fun getItemCount(): Int {
        return searchResults.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind( searchResults[position])
    }

    fun updateSearchResults(list: List<Media>) {
        this.searchResults.clear()
        this.searchResults.addAll(list)
        notifyDataSetChanged()

    }
}