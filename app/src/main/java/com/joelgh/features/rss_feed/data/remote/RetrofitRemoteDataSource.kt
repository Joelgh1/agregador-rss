package com.joelgh.features.rss_feed.data.remote

import com.joelgh.features.rss_feed.data.remote.api_client.ApiClient

class RetrofitRemoteDataSource(private val apiClient: ApiClient) : RssFeedRemoteDataSource {
}