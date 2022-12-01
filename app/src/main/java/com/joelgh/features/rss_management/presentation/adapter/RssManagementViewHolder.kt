package com.joelgh.features.rss_management.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.joelgh.features.rss_management.domain.Rss
import com.joelgh.rss_aggregator.databinding.ViewItemRssManagementBinding

class RssManagementViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(rss: Rss){
        val binding = ViewItemRssManagementBinding.bind(view)
        binding.apply {
            rssName.text = rss.name
            rssUrl.text = rss.url
        }
    }
}