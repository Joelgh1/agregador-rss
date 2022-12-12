package com.joelgh.features.rss_management.data

import com.joelgh.app.commons.error_management.*
import com.joelgh.features.rss_management.data.local.LocalDataSource
import com.joelgh.features.rss_management.domain.Rss
import com.joelgh.features.rss_management.domain.RssRepository
import kotlinx.coroutines.flow.Flow

class RssDataRepository(private val localSource: LocalDataSource) : RssRepository {

    override suspend fun create(rss: Rss) = localSource.create(rss)

    override suspend fun delete(url: String): Either<ErrorApp, Boolean> {
        return localSource.delete(url)
    }

    override suspend fun getAll(): Flow<Either<ErrorApp, List<Rss>>> = localSource.getAll()

}