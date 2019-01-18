package com.murgupluoglu.kotlinmvvm.utils

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Mustafa Urgupluoglu on 18.01.2019.
 */

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {
    if (!TextUtils.isEmpty(url)) {
        Glide.with(view).load(url).apply(RequestOptions.fitCenterTransform()).into(view)
    }
}