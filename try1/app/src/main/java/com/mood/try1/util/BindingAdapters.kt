package com.mood.try1.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Binding adapter used to hide the spinner once data is available.
 */
@BindingAdapter("isNetworkError")
fun hideIfNetworkError(view: View, isNetWorkError: Boolean) {
    if(isNetWorkError) {
        view.visibility = View.GONE
    }
}

@BindingAdapter("marketList")
fun hideIfNetworkError2(view: View, marketlist: Any?) {
    view.visibility = if (marketlist != null) View.GONE else View.VISIBLE

}

/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}