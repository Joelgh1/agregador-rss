package com.joelgh.features.rss_feed.data.local

import android.content.Context
import com.joelgh.app.commons.KSerializer
import com.joelgh.app.commons.error_management.Either
import com.joelgh.app.commons.error_management.ErrorApp
import com.joelgh.app.commons.error_management.left
import com.joelgh.app.commons.error_management.right
import com.joelgh.features.rss_feed.domain.NewsRssModel
import com.joelgh.rss_aggregator.R

class XmlLocalDataSource(private val context: Context,
                         private val serializer: KSerializer): RssFeedLocalDataSource {

    private val sharedPrefs = context.getSharedPreferences(context.getString(R.string.rss_feed_local),
        Context.MODE_PRIVATE)

    override fun save(url: String, feedModel: NewsRssModel) {
        val time = System.currentTimeMillis()
        sharedPrefs.edit().apply{
            putString(url, serializer.toJson(feedModel, NewsRssModel::class.java))
            putLong(url + "t", time)
        }
    }

    override fun get(url: String): Either<ErrorApp, NewsRssModel> {

        return if(sharedPrefs.contains(url)){
            val time = sharedPrefs.getLong(url + "t", 0)
            if(time + 1800000 >= System.currentTimeMillis()){
                ErrorApp.DataError().left()
            }else{
                serializer.fromJson(sharedPrefs.getString(url, null),
                    NewsRssModel::class.java).right()
            }
        }else{
            ErrorApp.DataError().left()
        }
    }
}