package com.joelgh.features.rss_management.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.joelgh.rss_aggregator.R
import com.joelgh.rss_aggregator.databinding.FragmentRssManagementBinding

class RssManagementFragment : Fragment() {
    private var binding: FragmentRssManagementBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRssManagementBinding.inflate(inflater, container, false)
        setUpToolBar()
        return binding?.root
    }

    private fun setUpToolBar(){
        binding?.apply {
            rssManagementToolBar.inflateMenu(R.menu.mangement_tool_bar_menu)
        }
    }

}