package com.route.news.adapter

import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageURL")
fun loadImageToImageVIew(imageView: ImageView, url: String) {
    Picasso.get().load(url).into(imageView)
}