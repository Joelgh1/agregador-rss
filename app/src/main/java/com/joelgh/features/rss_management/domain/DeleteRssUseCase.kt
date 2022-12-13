package com.joelgh.features.rss_management.domain

import com.joelgh.app.commons.error_management.Either
import com.joelgh.app.commons.error_management.ErrorApp

class DeleteRssUseCase(private val repository: RssRepository) {

    suspend fun execute(url: String): Either<ErrorApp, Boolean>{
        return repository.delete(url)
    }
}