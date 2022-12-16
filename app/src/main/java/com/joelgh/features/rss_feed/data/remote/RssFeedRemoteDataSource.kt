package com.joelgh.features.rss_feed.data.remote

import com.joelgh.features.rss_feed.domain.NewsRssModel

interface RssFeedRemoteDataSource {
    suspend fun getNews(url: String): NewsRssModel
}