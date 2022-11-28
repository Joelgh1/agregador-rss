package com.joelgh.features.rss_management.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joelgh.app.commons.error_management.Either
import com.joelgh.app.commons.error_management.ErrorApp
import com.joelgh.app.commons.error_management.left
import com.joelgh.features.rss_management.domain.GetRssUseCase
import com.joelgh.features.rss_management.domain.Rss
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RssManagementViewModel(private val getRss: GetRssUseCase) : ViewModel(){

    val rssPublisher: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>()
    }

    fun getRss() {
        rssPublisher.value = UiState(true)
        viewModelScope.launch(Dispatchers.IO) {
            val rss = getRss.execute()
            rssPublisher.postValue(UiState(false, rss))
        }
    }

    data class UiState(
        val isLoading: Boolean,
        val rssList: Either<ErrorApp, List<Rss>> = ErrorApp.DataError().left()
    )
}