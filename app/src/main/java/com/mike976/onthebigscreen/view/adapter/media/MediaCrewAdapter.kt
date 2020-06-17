package com.mike976.onthebigscreen.view.adapter.media

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onthebigscreen.R
import com.mike976.onthebigscreen.extensions.inflate
import com.mike976.onthebigscreen.model.Crew
import com.mike976.onthebigscreen.util.getProgressDrawable
import com.mike976.onthebigscreen.util.loadImage
import kotlinx.android.synthetic.main.list_item_person_info.view.*

class MediaCrewAdapter(private val crewList: MutableList<Crew>) : RecyclerView.Adapter<MediaCrewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_person_info))
    }

    override fun getItemCount(): Int {
        return crewList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(crewList[position])
    }

    fun updateCrewList(crewList: List<Crew>) {
        this.crewList.clear()
        this.crewList.addAll(crewList)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val progressDrawable = getProgressDrawable(itemView.context)

        fun bind(crew: Crew) {

            itemView.nameTextView.text = crew.name
            itemView.infoTextView.text = crew.job
            if (crew.profilePath != null) {
                itemView.photoImage.loadImage(crew.profilePathUrl, progressDrawable)
            }
        }
    }
}