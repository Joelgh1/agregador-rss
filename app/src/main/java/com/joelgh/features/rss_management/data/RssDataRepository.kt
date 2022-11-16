package com.joelgh.features.rss_management.data

import com.joelgh.app.commons.error_management.*
import com.joelgh.features.rss_management.data.local.LocalDataSource
import com.joelgh.features.rss_management.domain.Rss
import com.joelgh.features.rss_management.domain.RssRepository

class RssDataRepository(private val localSource: LocalDataSource) : RssRepository {

    override suspend fun create(rss: Rss) = localSource.create(rss)

    override fun delete(rss: Rss) {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): Either<ErrorApp, List<Rss>> = localSource.getAll()

}