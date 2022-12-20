package com.joelgh.features.rss_feed.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.joelgh.app.commons.loadUrl
import com.joelgh.features.rss_feed.domain.GetRssFeedUseCase
import com.joelgh.rss_aggregator.R
import com.joelgh.rss_aggregator.databinding.ViewItemRssFeedBinding

class RssFeedViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(feed: GetRssFeedUseCase.NewRss){
        val binding = ViewItemRssFeedBinding.bind(view)
        binding.apply {
            newsTitle.text = feed.title
            newsDescription.text = feed.description
            newsSource.text = feed.source
            if(feed.image == null){
                newsImage.setImageResource(R.drawable.no_image_ic)
            }else{
                newsImage.loadUrl(feed.image)
            }
        }
    }
}