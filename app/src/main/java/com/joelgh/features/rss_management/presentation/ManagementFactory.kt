package com.joelgh.features.rss_management.presentation

import android.content.Context
import com.joelgh.app.commons.GsonSerializer
import com.joelgh.features.rss_feed.presentation.RssFeedFragmentDirections
import com.joelgh.features.rss_management.data.RssDataRepository
import com.joelgh.features.rss_management.data.local.LocalDataSource
import com.joelgh.features.rss_management.data.local.XmlLocalDataSource
import com.joelgh.features.rss_management.domain.DeleteRssUseCase
import com.joelgh.features.rss_management.domain.GetRssUseCase
import com.joelgh.features.rss_management.domain.RssRepository
import com.joelgh.features.rss_management.domain.SaveRssUseCase

class ManagementFactory {
    companion object{
        fun getAddRssViewModel(context: Context): AddRssFormViewModel{
            return AddRssFormViewModel(SaveRssUseCase(getRssRepository(context)))
        }

        fun getRssManagementViewModel(context: Context): RssManagementViewModel{
            return RssManagementViewModel(GetRssUseCase(getRssRepository(context)),
                DeleteRssUseCase(getRssRepository(context)))
        }

        private fun getRssRepository(context: Context): RssRepository{
            return RssDataRepository(getLocalSource(context))
        }

        private fun getLocalSource(context: Context): LocalDataSource{
            return XmlLocalDataSource(context, GsonSerializer())
        }
    }
}