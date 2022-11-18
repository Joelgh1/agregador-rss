package com.joelgh.features.rss_management.domain

class SaveRssUseCase(private val repository: RssRepository) {

    fun execute(name: String, url: String){
        repository.create(Rss(name, url))
    }

}