package com.joelgh.features.rss_feed.presentation

import android.content.Context
import com.joelgh.app.commons.GsonSerializer
import com.joelgh.features.rss_feed.data.NewsRssDataRepository
import com.joelgh.features.rss_feed.data.local.RssFeedLocalDataSource
import com.joelgh.features.rss_feed.data.local.XmlLocalDataSource
import com.joelgh.features.rss_feed.data.remote.RetrofitRemoteDataSource
import com.joelgh.features.rss_feed.data.remote.RssFeedRemoteDataSource
import com.joelgh.features.rss_feed.data.remote.api_client.ApiClient
import com.joelgh.features.rss_feed.domain.GetRssFeedUseCase
import com.joelgh.features.rss_feed.domain.NewsRssRepository
import com.joelgh.features.rss_management.data.RssDataRepository
import com.joelgh.features.rss_management.data.local.DsLocalDataSource
import com.joelgh.features.rss_management.data.local.LocalDataSource
import com.joelgh.features.rss_management.domain.RssRepository

class FeedFactory {
    companion object{
        fun getFeedRssViewModel(context: Context): RssFeedViewModel {
            return RssFeedViewModel(GetRssFeedUseCase(getRssRepository(context), getNewsRepository(context)))
        }

        private fun getNewsRepository(context: Context): NewsRssRepository{
            return NewsRssDataRepository(getRemoteSource(), getFeedLocalSource(context))
        }

        private fun getRssRepository(context: Context): RssRepository {
            return RssDataRepository(getLocalSource(context))
        }

        private fun getLocalSource(context: Context): LocalDataSource {
            return DsLocalDataSource(context, GsonSerializer())
        }

        private fun getRemoteSource(): RssFeedRemoteDataSource{
            return RetrofitRemoteDataSource(apiClient)
        }

        private fun getFeedLocalSource(context: Context): RssFeedLocalDataSource{
            return XmlLocalDataSource(context, GsonSerializer())
        }

        private val apiClient = ApiClient()

    }
}