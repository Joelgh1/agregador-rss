package com.joelgh.features.rss_feed.data.remote

import com.joelgh.features.rss_feed.domain.GetRssFeedUseCase
import com.joelgh.features.rss_feed.domain.NewsRssModel

fun RssFeedApiModel.toDomain() = NewsRssModel(this.items?.map { news ->
    news.toDomain()
})
fun ItemApiModel.toDomain() = GetRssFeedUseCase.NewRss(this.title, this.thumbnail, this.creator, this.description)