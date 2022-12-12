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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RssManagementViewModel(private val getRss: GetRssUseCase,
                             private val deleteRss: DeleteRssUseCase) : ViewModel(){

    val rssPublisher: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>()
    }

    fun getRss() {
        rssPublisher.value = UiState(true)
        viewModelScope.launch(Dispatchers.IO) {
            getRss.execute().collect{
                it.fold({ errorApp ->
                    rssPublisher.postValue(
                        UiState(
                            false,
                            errorApp
                        )
                    )
                },{ rssList ->
                    rssPublisher.postValue(
                        UiState(
                            false,
                            null,
                            rssList
                        )
                    )
                })
            }
        }
    }

    fun deleteRss(url: String){
        viewModelScope.launch(Dispatchers.IO){
            val result = deleteRss.execute(url)
            when(result){
                is Either.Left -> rssPublisher.postValue(
                    UiState(false, result.value)
                )
                is Either.Right -> {
                    rssPublisher.postValue(
                        UiState(false, null, deleteSuccess = true)
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