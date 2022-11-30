package com.joelgh.features.rss_management.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.joelgh.app.commons.error_management.ErrorApp
import com.joelgh.app.commons.showSnackBar
import com.joelgh.features.rss_management.presentation.adapter.RssManagementAdapter
import com.joelgh.rss_aggregator.NavGraphDirections
import com.joelgh.rss_aggregator.R
import com.joelgh.rss_aggregator.databinding.FragmentRssManagementBinding

class RssManagementFragment : Fragment() {
    private var binding: FragmentRssManagementBinding? = null
    private var viewModel: RssManagementViewModel? = null
    private val rssAdapter = RssManagementAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRssManagementBinding.inflate(inflater, container, false)
        setUpView()
        viewModel = ManagementFactory.getRssManagementViewModel(requireContext())
        return binding?.root
    }

    private fun setUpView(){
        binding?.apply {
            rssManagementRecycler.apply {
                adapter = rssAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }
            rssManagementToolBar.inflateMenu(R.menu.management_tool_bar_menu)
            rssManagementToolBar.setOnMenuItemClickListener{
                when(it.itemId){
                    R.id.action_add -> showForm()
                }
                true
            }
        }
    }

    private fun showForm(){
        findNavController().navigate(NavGraphDirections.actionFormData())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        viewModel?.getRss()
    }

    private fun setUpObserver(){
        val suscriber = Observer<RssManagementViewModel.UiState>{
            if(it.isLoading){
                //Codigo de pantalla de carga
            }else{
                if(it.error == null){
                    rssAdapter.setDataItems(it.rssList!!)
                    rssAdapter.setOnClickItem {
                        viewModel?.deleteRss(it)
                    }
                    if(it.deleteSuccess)showSnackBar(getString(R.string.delete_success))
                }else{
                    rssAdapter.setDataItems(it.rssList!!)
                    rssAdapter.setOnClickItem {
                        viewModel?.deleteRss(it)
                    }
                    when(it.error){
                        is ErrorApp.DataError -> showSnackBar(getString(R.string.no_rss_found))
                        is ErrorApp.DeleteError -> showSnackBar(getString(R.string.delete_error))
                    }
                }
            }
        }

        viewModel?.rssPublisher?.observe(viewLifecycleOwner, suscriber)
    }

}