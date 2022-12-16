package com.joelgh.features.rss_feed.domain

import com.joelgh.app.commons.error_management.Either
import com.joelgh.app.commons.error_management.ErrorApp
import com.joelgh.app.commons.error_management.right
import com.joelgh.features.rss_management.domain.RssRepository
import kotlinx.coroutines.flow.map

class GetRssFeedUseCase(private val rssRepository: RssRepository, private val newsRssRepository: NewsRssRepository) {

    suspend fun execute(): Either<ErrorApp, List<NewsRssModel>> {
        val news = mutableListOf<NewsRssModel>()
        rssRepository.getAll().map {
            it.get().forEach { rss ->
                news.add(newsRssRepository.getNews(rss.url))
            }
        }
        return news.right()
    }

    data class NewRss(
        val title: String?,
        val image: String?,
        val source: String?,
        val description: String?
    )
}