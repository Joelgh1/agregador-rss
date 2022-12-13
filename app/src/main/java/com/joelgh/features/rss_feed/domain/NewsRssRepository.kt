package com.joelgh.features.rss_feed.domain

interface NewsRssRepository {
    suspend fun getNews(url: String): NewsRssModel
}