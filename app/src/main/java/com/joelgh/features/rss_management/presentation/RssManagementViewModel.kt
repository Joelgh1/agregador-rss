package com.joelgh.features.rss_management.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joelgh.app.commons.error_management.Either
import com.joelgh.app.commons.error_management.ErrorApp
import com.joelgh.app.commons.error_management.left
import com.joelgh.features.rss_management.domain.DeleteRssUseCase
import com.joelgh.features.rss_management.domain.GetRssUseCase
import com.joelgh.features.rss_management.domain.Rss
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RssManagementViewModel(private val getRss: GetRssUseCase,
                             private val deleteRss: DeleteRssUseCase) : ViewModel(){

    val rssPublisher: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>()
    }

    private var rssList: List<Rss>? = null

    fun getRss() {
        rssPublisher.value = UiState(true)
        viewModelScope.launch(Dispatchers.IO) {
            val rss = getRss.execute()
            when(rss){
                is Either.Left -> rssPublisher.postValue(
                    UiState(false, rss.value)
                )
                is Either.Right -> {
                    rssList = rss.value
                    rssPublisher.postValue(
                        UiState(false, null, rss.value)
                    )
                }

            }
        }
    }

    fun deleteRss(name: String){
        viewModelScope.launch(Dispatchers.IO){
            val result = deleteRss.execute(name)
            when(result){
                is Either.Left -> rssPublisher.postValue(
                    UiState(false, result.value)
                )
                is Either.Right -> {
                    rssPublisher.postValue(
                        UiState(false, null, rssList, true)
                    )
                }
            }
        }
    }

    data class UiState(
        val isLoading: Boolean,
        val error: ErrorApp? = null,
        val rssList: List<Rss>? = emptyList(),
        val deleteSuccess: Boolean = false
    )
}