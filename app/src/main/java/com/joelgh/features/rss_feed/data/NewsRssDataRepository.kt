package com.joelgh.features.rss_feed.data

import com.joelgh.app.commons.error_management.Either
import com.joelgh.app.commons.error_management.getOrElse
import com.joelgh.features.rss_feed.data.local.RssFeedLocalDataSource
import com.joelgh.features.rss_feed.data.remote.RssFeedRemoteDataSource
import com.joelgh.features.rss_feed.domain.NewsRssModel
import com.joelgh.features.rss_feed.domain.NewsRssRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsRssDataRepository(private val remoteSource: RssFeedRemoteDataSource,
                            private val localSource: RssFeedLocalDataSource) : NewsRssRepository {

    override suspend fun getNews(url: String): NewsRssModel {
        localSource.get(url).fold({
            val news = remoteSource.getNews(url)
            localSource.save(url, news)
            return news
        },{
            return it
        })
    }
}