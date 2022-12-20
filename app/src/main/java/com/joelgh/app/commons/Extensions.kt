package com.joelgh.app.commons

import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackBar(text: String){
    Snackbar.make(
        requireView(),
        text,
        Snackbar.LENGTH_SHORT
    ).show()
}

fun ImageView.loadUrl(url: String){
    Glide.with(this).load(url).into(this)
}