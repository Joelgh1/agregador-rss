package com.joelgh.features.rss_feed.domain

import com.joelgh.app.commons.error_management.Either
import com.joelgh.app.commons.error_management.ErrorApp
import com.joelgh.app.commons.error_management.right
import com.joelgh.features.rss_management.domain.RssRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class GetRssFeedUseCase(
    private val rssRepository: RssRepository,
    private val newsRssRepository: NewsRssRepository
) {

    suspend fun execute(): Flow<Either<ErrorApp, List<NewsRssModel>>> {
        return rssRepository
            .getAll()
            .map {
                val newRssList = mutableListOf<NewsRssModel>()
                it.get().forEach { rss ->
                    val newRss = newsRssRepository.getNews(rss.url)
                    newRssList.add(newRss)
                }
                newRssList.right()
            }
    }

    data class NewRss(
        val title: String?,
        val image: String?,
        val source: String?,
        val description: String?
    )
}