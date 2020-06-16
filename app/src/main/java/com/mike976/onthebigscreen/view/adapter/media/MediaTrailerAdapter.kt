package com.mike976.onthebigscreen.view.adapter.media

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.onthebigscreen.R
import com.mike976.onthebigscreen.extensions.inflate
import com.mike976.onthebigscreen.model.Trailer
import com.mike976.onthebigscreen.util.getProgressDrawable
import com.mike976.onthebigscreen.util.loadImage
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.list_item_trailer.view.*


class MediaTrailerAdapter(
    private val trailers: MutableList<Trailer>
) : RecyclerView.Adapter<MediaTrailerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_trailer))
    }

    override fun getItemCount(): Int {
        return trailers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trailers[position])
    }

    fun updateTrailers(trailers: List<Trailer>) {
        this.trailers.clear()
        this.trailers.addAll(trailers)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val progressDrawable = getProgressDrawable(itemView.context)

        fun bind(trailer: Trailer) {

//                itemView.mediaImage.loadImage(trailer.youtubeUrlThumbnail, progressDrawable)
//                itemView.mediaImage.setOnClickListener{
//
//                }


                itemView.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    if(trailer.key != null) {
                        youTubePlayer.cueVideo(trailer.key, 1f)
                    }

                }
            })

        }
    }
}