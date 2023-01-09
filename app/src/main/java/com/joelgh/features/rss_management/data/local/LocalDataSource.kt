package com.joelgh.features.rss_management.data.local

import com.joelgh.app.commons.error_management.Either
import com.joelgh.app.commons.error_management.ErrorApp
import com.joelgh.features.rss_management.domain.Rss

interface LocalDataSource {
    suspend fun create(name: String, url: String)
    suspend fun getAll(): Either<ErrorApp, List<Rss>>
}