package com.joelgh.features.rss_feed.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.joelgh.features.rss_feed.domain.GetRssFeedUseCase
import com.joelgh.rss_aggregator.R

class RssFeedAdapter : RecyclerView.Adapter<RssFeedViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<GetRssFeedUseCase.NewRss>() {
        override fun areItemsTheSame(
            oldItem: GetRssFeedUseCase.NewRss,
            newItem: GetRssFeedUseCase.NewRss
        ): Boolean {
            return oldItem.title == newItem.title
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: GetRssFeedUseCase.NewRss,
            newItem: GetRssFeedUseCase.NewRss
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssFeedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_rss_feed, parent, false)
        return RssFeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: RssFeedViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = differ.currentList.size
}

