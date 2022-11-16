package com.joelgh.features.rss_feed.data.remote.api_client

import com.joelgh.features.rss_feed.data.remote.RssFeedApiModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiEndPoints {

    @GET
    fun getFeed(@Url url: String): Call<List<RssFeedApiModel>>
}