package com.joelgh.features.rss_feed.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.joelgh.app.commons.showSnackBar
import com.joelgh.features.rss_feed.presentation.adapter.RssFeedAdapter
import com.joelgh.rss_aggregator.R
import com.joelgh.rss_aggregator.databinding.FragmentRssFeedBinding

class RssFeedFragment : Fragment() {

    private var binding: FragmentRssFeedBinding? = null
    private var feedAdapter = RssFeedAdapter()
    private var viewModel: RssFeedViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRssFeedBinding.inflate(layoutInflater, container, false)
        viewModel = FeedFactory.getFeedRssViewModel(requireContext())
        setUpView()
        return binding?.root
    }

    private fun setUpView(){
        binding?.apply {
            rssFeedItemList.apply {
                adapter = feedAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        viewModel?.getNews()
    }

    private fun setUpObservers(){
        val suscriber = Observer<RssFeedViewModel.UiState>{
            if(it.error != null){
                showSnackBar(getString(R.string.generic_error))
            }else{
                if(it.isLoading){
                    //TODO
                }else{
                    feedAdapter.differ.submitList(it.feed)
                }
            }
        }

        viewModel?.feedPublisher?.observe(viewLifecycleOwner, suscriber)
    }
}