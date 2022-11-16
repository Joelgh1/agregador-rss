package com.joelgh.features.rss_management.domain

interface RssRepository {
    suspend fun create(rss: Rss)
    fun delete(rss: Rss)
    suspend fun getAll(): List<Rss>
}