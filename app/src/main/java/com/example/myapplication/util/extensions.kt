package com.example.myapplication.util

import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

fun ImageView.load(url: String?) {
    val requestCreator =
        if (url == null)
            Picasso.get().load(R.drawable.placeholder_nomoon)
        else
            Picasso.get().load(url)

    requestCreator
        .fit()
        .placeholder(R.drawable.image_loading_progress)
        .error(R.drawable.placeholder_nomoon)
        .memoryPolicy(MemoryPolicy.NO_STORE)
        .into(this)
}

fun Fragment.disableUI(disable: Boolean) {
    if (disable)
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    else
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun View.setMarginTop(marginTop: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(0, marginTop, 0, 0)
    this.layoutParams = menuLayoutParams
}