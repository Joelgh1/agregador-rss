package com.joelgh.features.rss_feed.domain

import com.joelgh.features.rss_management.domain.RssRepository

class GetRssFeedUseCase(private val rssRepository: RssRepository,
                        private val rssNewsRepository: NewsRepository) {

    fun execute(url: String){

    }

    data class RssFeedModel(
        val image: String,
        val title: String,
        val source: String,
        val summary: String,
        val date: String
    )
}