package com.joelgh.features.rss_management.domain

import com.joelgh.app.commons.error_management.Either
import com.joelgh.app.commons.error_management.ErrorApp

class GetRssUseCase(private val repository: RssRepository) {

    suspend fun execute(): Either<ErrorApp, List<Rss>> = repository.getAll()
}