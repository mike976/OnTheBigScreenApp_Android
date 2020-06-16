package com.mike976.onthebigscreen.view.adapter.media

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onthebigscreen.R
import com.mike976.onthebigscreen.extensions.inflate
import com.mike976.onthebigscreen.model.ProductionCompany
import com.mike976.onthebigscreen.util.getProgressDrawable
import com.mike976.onthebigscreen.util.loadImage
import kotlinx.android.synthetic.main.list_item_production_company.view.*

class MediaProductionAdapter(private val productionCompanies: MutableList<ProductionCompany>) : RecyclerView.Adapter<MediaProductionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_production_company, false))
    }

    override fun getItemCount(): Int {
        return productionCompanies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productionCompanies[position])
    }

    fun updateProductionCompanies(productionCompanies: List<ProductionCompany>) {
        this.productionCompanies.clear()
        this.productionCompanies.addAll(productionCompanies)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val progressDrawable = getProgressDrawable(itemView.context)

        fun bind(productionCompany: ProductionCompany) {

            itemView.productionCompanyImage.loadImage(productionCompany.logoUrl, progressDrawable)

        }

    }


}