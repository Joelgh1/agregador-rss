package com.joelgh.features.rss_feed.data

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
        return localSource.get(url).getOrElse {
            var news: NewsRssModel? = null
            GlobalScope.launch {
                news = remoteSource.getNews(url)
            }
            news?.let {
                localSource.save(url, it)
            }
            news!!
        }
    }
}