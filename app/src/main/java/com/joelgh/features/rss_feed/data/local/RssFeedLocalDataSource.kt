package com.joelgh.features.rss_feed.data.local

import com.joelgh.app.commons.error_management.Either
import com.joelgh.app.commons.error_management.ErrorApp
import com.joelgh.features.rss_feed.domain.NewsRssModel

interface RssFeedLocalDataSource {
    fun save(url: String, feedModel: NewsRssModel)
    fun get(url: String): Either<ErrorApp, NewsRssModel>
}