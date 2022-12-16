package com.joelgh.features.rss_feed.data.remote

import com.joelgh.features.rss_feed.data.remote.api_client.ApiClient
import com.joelgh.features.rss_feed.domain.NewsRssModel

class RetrofitRemoteDataSource(private val apiClient: ApiClient) : RssFeedRemoteDataSource {
    override suspend fun getNews(url: String): NewsRssModel = apiClient.getFeed(url)!!.toDomain()
}