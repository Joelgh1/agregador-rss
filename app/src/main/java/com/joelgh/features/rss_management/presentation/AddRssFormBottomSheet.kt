package com.joelgh.features.rss_management.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.joelgh.rss_aggregator.databinding.BottomSheetBinding

class AddRssFormBottomSheet : BottomSheetDialogFragment() {

    private var binding: BottomSheetBinding? = null
    private var viewModel: AddRssFormViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetBinding.inflate(inflater)
        viewModel = ManagementFactory.getAddRssViewModel(requireContext())
        setUpView()
        return binding?.root
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
}