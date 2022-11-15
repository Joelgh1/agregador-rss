package com.joelgh.features.rss_management.data.local

import android.content.Context
import androidx.core.content.edit
import com.joelgh.app.commons.KSerializer
import com.joelgh.features.rss_management.domain.Rss

class XmlLocalDataSource(private val context: Context, private val serializer: KSerializer) : LocalDataSource{

    private val sharedPrefs = context.getSharedPreferences("Rss", Context.MODE_PRIVATE)

    override fun create(rss: Rss) {
        sharedPrefs.edit {
            putString(rss.name, serializer.toJson(rss, Rss::class.java))
        }
    }

    override fun getAll(): List<Rss> = sharedPrefs.all.map { entry ->
            serializer.fromJson(entry.value.toString(), Rss::class.java)
        }


}