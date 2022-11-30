package com.joelgh.app.commons

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(text: String){
    Snackbar.make(
        this,
        text,
        Snackbar.LENGTH_SHORT
    ).show()
}