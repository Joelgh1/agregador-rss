package com.joelgh.features.rss_feed.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joelgh.app.commons.error_management.ErrorApp
import com.joelgh.features.rss_feed.domain.GetRssFeedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RssFeedViewModel(private val getFeed: GetRssFeedUseCase) : ViewModel() {

    private val _feedPublisher: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>()
    }

    val feedPublisher = _feedPublisher

    fun getNews() {

        _feedPublisher.postValue(UiState(true))

        viewModelScope.launch(Dispatchers.IO) {
            val feed = getFeed.execute()
            feed.fold({ errorApp ->
                _feedPublisher.postValue(
                    UiState(
                        false,
                        errorApp
                    )
                )
            }, { feedsList ->
                val feedList = mutableListOf<GetRssFeedUseCase.NewRss>()

                feedsList.forEach { newsRssModel ->
                    newsRssModel.news?.forEach { news ->
                        feedList.add(news)
                    }
                }

                _feedPublisher.postValue(
                    UiState(
                        false,
                        feed = feedList
                    )
                )
            })
        }
    }

    data class UiState(
        val isLoading: Boolean,
        val error: ErrorApp? = null,
        val feed: List<GetRssFeedUseCase.NewRss> = emptyList()
    )
}