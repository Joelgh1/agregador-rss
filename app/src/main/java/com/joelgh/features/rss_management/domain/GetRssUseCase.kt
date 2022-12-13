package com.joelgh.features.rss_management.domain

import com.joelgh.app.commons.error_management.Either
import com.joelgh.app.commons.error_management.ErrorApp
import kotlinx.coroutines.flow.Flow

class GetRssUseCase(private val repository: RssRepository) {

    suspend fun execute(): Flow<Either<ErrorApp, List<Rss>>> = repository.getAll()
}