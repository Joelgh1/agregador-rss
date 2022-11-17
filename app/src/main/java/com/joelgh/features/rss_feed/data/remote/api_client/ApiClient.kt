package com.joelgh.features.rss_feed.data.remote.api_client

import com.joelgh.features.rss_feed.data.remote.RssFeedApiModel
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class ApiClient {
    private val baseUrl = "https://google.es/"

    private val apiEndPoints: ApiEndPoints

    init {
        apiEndPoints = buildClient().create(ApiEndPoints::class.java)
    }

    private fun buildClient() = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(SimpleXmlConverterFactory.create())
    .build()

    suspend fun getFeed(url: String) = apiEndPoints.getFeed(url).body()
}