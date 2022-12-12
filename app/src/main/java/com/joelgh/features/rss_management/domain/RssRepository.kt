package com.joelgh.features.rss_management.domain

import com.joelgh.app.commons.error_management.Either
import com.joelgh.app.commons.error_management.ErrorApp
import kotlinx.coroutines.flow.Flow

interface RssRepository {
    suspend fun create(rss: Rss): Either<ErrorApp, Boolean>
    suspend fun getAll(): Flow<Either<ErrorApp, List<Rss>>>
    suspend fun delete(url: String): Either<ErrorApp, Boolean>
}