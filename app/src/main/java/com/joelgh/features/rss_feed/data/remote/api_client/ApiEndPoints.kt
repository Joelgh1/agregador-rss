package com.joelgh.features.rss_feed.data.remote.api_client

import com.joelgh.features.rss_feed.data.remote.RssFeedApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiEndPoints {

    @GET
    suspend fun getFeed(@Url url: String): Response<RssFeedApiModel>
}