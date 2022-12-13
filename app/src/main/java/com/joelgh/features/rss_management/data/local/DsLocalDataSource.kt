package com.joelgh.features.rss_management.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.joelgh.app.commons.KSerializer
import com.joelgh.app.commons.error_management.Either
import com.joelgh.app.commons.error_management.ErrorApp
import com.joelgh.app.commons.error_management.left
import com.joelgh.app.commons.error_management.right
import com.joelgh.features.rss_management.domain.Rss
import com.joelgh.rss_aggregator.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "Rss_datastore"//context.getString(R.string.datastore_file)
)

class DsLocalDataSource(private val context: Context, private val serializer: KSerializer) : LocalDataSource{



    override suspend fun create(rss: Rss): Either<ErrorApp, Boolean> {
        return try {
            context.dataStore.edit {
                it[stringPreferencesKey(rss.url)] = serializer.toJson(rss, Rss::class.java)
            }
            true.right()
        }catch (e: Exception){
            ErrorApp.DataError().left()
        }
    }

    override suspend fun getAll(): Flow<Either<ErrorApp, List<Rss>>> {
        return context.dataStore.data.map {
            it.asMap().values.map {
                serializer.fromJson(it as String, Rss::class.java)
            }.right()
        }
    }

    override suspend fun delete(url: String): Either<ErrorApp, Boolean> {
        return try {
            context.dataStore.apply {
                this.edit {
                    it.remove(stringPreferencesKey(url))
                }
            }
            true.right()
        }catch (e: Exception){
            ErrorApp.DataError().left()
        }
    }
}