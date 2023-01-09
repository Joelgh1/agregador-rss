package com.joelgh.features.rss_management.data.local

import android.content.Context
import androidx.core.content.edit
import com.joelgh.app.commons.KSerializer
import com.joelgh.app.commons.error_management.Either
import com.joelgh.app.commons.error_management.ErrorApp
import com.joelgh.app.commons.error_management.left
import com.joelgh.app.commons.error_management.right
import com.joelgh.features.rss_management.domain.Rss
import com.joelgh.rss_aggregator.R

class XmlLocalDataSource(private val context: Context, private val serializer: KSerializer) : LocalDataSource{

    private val sharedPrefs = context.getSharedPreferences(context.getString(R.string.management_shared_prefs), Context.MODE_PRIVATE)


    override suspend fun create(rss: Rss): Either<ErrorApp, Boolean> {
        try {
            sharedPrefs.edit {
                putString(rss.name, serializer.toJson(rss, Rss::class.java))
            }
            return true.right()
        }catch (e: java.lang.Exception){
            return ErrorApp.DataError().left()
        }
    }

    override suspend fun getAll(): Either<ErrorApp, List<Rss>> {

        val rssList = sharedPrefs.all.map { entry ->
            serializer.fromJson(entry.value.toString(), Rss::class.java)
        }

        return if(rssList.isEmpty()){
            ErrorApp.DataError().left()
        }else{
            rssList.right()
        }
    }


}