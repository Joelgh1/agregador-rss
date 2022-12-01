package com.joelgh.features.rss_management.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joelgh.features.rss_management.domain.Rss
import com.joelgh.rss_aggregator.R

class RssManagementAdapter : RecyclerView.Adapter<RssManagementViewHolder>() {

    private val dataItems = mutableListOf<Rss>()

    fun setDataItems(rssList: List<Rss>) {
        dataItems.clear()
        dataItems.addAll(rssList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssManagementViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_rss_management, parent, false)
        return RssManagementViewHolder(view)
    }

    override fun onBindViewHolder(holder: RssManagementViewHolder, position: Int) {
        holder.bind(dataItems[position])
    }

    override fun getItemCount(): Int = dataItems.size
}