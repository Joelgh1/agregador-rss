package com.joelgh.app.commons

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.joelgh.rss_aggregator.R

fun Fragment.showErrorSnackBar(){
    Snackbar.make(
        requireView(),
        R.string.generic_error,
        Snackbar.LENGTH_SHORT
    ).show()
}