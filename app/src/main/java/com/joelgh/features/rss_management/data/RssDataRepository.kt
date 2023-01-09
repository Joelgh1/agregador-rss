package com.joelgh.features.rss_management.data

import com.joelgh.app.commons.error_management.*
import com.joelgh.features.rss_management.data.local.LocalDataSource
import com.joelgh.features.rss_management.domain.Rss
import com.joelgh.features.rss_management.domain.RssRepository

class RssDataRepository(private val localSource: LocalDataSource) : RssRepository {

    override suspend fun create(name: String, url: String) = localSource.create(name, url)

    override suspend fun getAll(): Either<ErrorApp, List<Rss>> = localSource.getAll()

}