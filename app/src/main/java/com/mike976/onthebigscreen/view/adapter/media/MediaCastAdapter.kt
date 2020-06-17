package com.mike976.onthebigscreen.view.adapter.media

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onthebigscreen.R
import com.mike976.onthebigscreen.extensions.inflate
import com.mike976.onthebigscreen.model.Cast
import com.mike976.onthebigscreen.util.getProgressDrawable
import com.mike976.onthebigscreen.util.loadImage
import kotlinx.android.synthetic.main.list_item_person_info.view.*

class MediaCastAdapter(private val castList: MutableList<Cast>) : RecyclerView.Adapter<MediaCastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_person_info))
    }

    override fun getItemCount(): Int {
        return castList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(castList[position])
    }

    fun updateCastList(castList: List<Cast>) {
        this.castList.clear()
        this.castList.addAll(castList)

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val progressDrawable = getProgressDrawable(itemView.context)

        fun bind(cast: Cast) {
            itemView.nameTextView.text = cast.name
            itemView.infoTextView.text = "as ${cast.character}"
            if (cast.profilePath != null) {
                itemView.photoImage.loadImage(cast.profilePathUrl, progressDrawable)
            }
        }
    }



}