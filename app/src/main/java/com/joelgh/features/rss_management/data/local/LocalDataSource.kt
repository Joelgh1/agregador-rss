package com.joelgh.features.rss_management.data.local

import com.joelgh.features.rss_management.domain.Rss

interface LocalDataSource {
    fun create(rss: Rss)
    fun getAll(): List<Rss>
}