package com.r2devpros.android_contactsapp.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["imageUrl"], requireAll = false)
fun imageUrl(view: ImageView, imageUrl: String?) {
    Glide.with(view).load(imageUrl).into(view)
}