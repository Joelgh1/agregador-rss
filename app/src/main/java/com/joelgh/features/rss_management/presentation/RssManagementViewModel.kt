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
            when(rss){
                is Either.Left -> rssPublisher.postValue(
                    UiState(false, rss.value)
                )
                is Either.Right -> rssPublisher.postValue(
                    UiState(false, rssList = rss.value)
                )
            }
        }
    }

    data class UiState(
        val isLoading: Boolean,
        val error: ErrorApp? = null,
        val rssList: List<Rss> = emptyList()
    )
}