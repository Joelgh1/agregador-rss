package com.joelgh.features.rss_management.domain

interface RssRepository {
    fun create(rss: Rss)
}