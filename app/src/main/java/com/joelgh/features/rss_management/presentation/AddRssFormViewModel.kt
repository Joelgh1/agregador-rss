package com.joelgh.features.rss_management.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joelgh.features.rss_management.domain.SaveRssUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddRssFormViewModel(private val saveRssUseCase: SaveRssUseCase) : ViewModel() {

    fun saveRss(name: String, url: String){
        viewModelScope.launch(Dispatchers.IO){
            saveRssUseCase.execute(name, url)
        }
    }
}