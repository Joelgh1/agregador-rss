package com.joelgh.features.rss_feed.domain

interface NewsRepository {
    suspend fun getNews(url: String): List<NewsRss>
}