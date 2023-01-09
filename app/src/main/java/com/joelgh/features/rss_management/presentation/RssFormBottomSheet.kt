package com.joelgh.features.rss_management.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.joelgh.app.commons.showSnackBar
import com.joelgh.rss_aggregator.R
import com.joelgh.rss_aggregator.databinding.RssFormBottomSheetBinding

class RssFormBottomSheet : BottomSheetDialogFragment() {

    private var binding: RssFormBottomSheetBinding? = null
    private var viewModel: AddRssFormViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RssFormBottomSheetBinding.inflate(inflater)
        viewModel = ManagementFactory.getAddRssViewModel(requireContext())
        setUpView()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
    }

    private fun setUpView(){
        binding?.apply {
            saveRssButton.setOnClickListener{
                viewModel?.saveRss(rssInputName.text.toString(), rssInputUrl.text.toString())
                findNavController().navigateUp()
            }
            cancelRssButton.setOnClickListener{
                findNavController().navigateUp()
            }
        }

    }

    private fun setUpObserver(){
        val suscriber = Observer<AddRssFormViewModel.UiModel>{
            if (it.isSuccess)
                requireActivity().findViewById<View>(R.id.nav_host_fragment).showSnackBar(getString(R.string.save_success))
            else {
                requireActivity().findViewById<View>(R.id.nav_host_fragment).showSnackBar(getString(R.string.save_error))
            }
        }

        viewModel?.publisher?.observe(viewLifecycleOwner, suscriber)
    }


}