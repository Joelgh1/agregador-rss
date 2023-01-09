package com.joelgh.features.rss_management.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import com.joelgh.app.commons.error_management.Either
import com.joelgh.features.rss_management.domain.SaveRssUseCase
import com.joelgh.rss_aggregator.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddRssFormViewModel(private val saveRssUseCase: SaveRssUseCase) : ViewModel() {

    val publisher: MutableLiveData<UiModel> by lazy{
        MutableLiveData<UiModel>()
    }

    fun saveRss(name: String, url: String){
        viewModelScope.launch(Dispatchers.IO){
                when(saveRssUseCase.execute(name, url)){
                    is Either.Left -> publisher.postValue(
                        UiModel(false)
                    )
                    is Either.Right -> publisher.postValue(
                        UiModel(true)
                    )
                }
        }
    }

    data class UiModel(
        var isSuccess: Boolean
    )

}