package com.joelgh.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joelgh.app.commons.GsonSerializer
import com.joelgh.features.rss_management.data.RssDataRepository
import com.joelgh.features.rss_management.data.local.XmlLocalDataSource
import com.joelgh.rss_aggregator.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repo = RssDataRepository(XmlLocalDataSource(this, GsonSerializer()))
        GlobalScope.launch {
            repo.create("RssPrueba", "UrlPrueba")
        }
    }

}