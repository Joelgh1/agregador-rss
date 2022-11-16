package com.joelgh.features.rss_management.data.local

import com.joelgh.features.rss_management.domain.Rss

interface LocalDataSource {
    suspend fun create(rss: Rss)
    suspend fun getAll(): List<Rss>
}