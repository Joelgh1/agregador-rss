package com.joelgh.app.commons

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showErrorSnackBar(text: String){
    Snackbar.make(
        requireView(),
        text,
        Snackbar.LENGTH_SHORT
    ).show()
}