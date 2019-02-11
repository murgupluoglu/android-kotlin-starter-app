package com.murgupluoglu.kotlinmvvm.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.murgupluoglu.kotlinmvvm.fragment.recyclerview.ViewPager2Adapter
import com.murgupluoglu.kotlinmvvm.fragment.recyclerview.ViewPagerAdapter
import com.murgupluoglu.kotlinmvvm.fragment.recyclerview.ViewPagerItem

/**
 * Created by Mustafa Urgupluoglu on 18.01.2019.
 */

@BindingAdapter("bindViewPager")
fun bindViewPager(viewPager: ViewPager, array: ArrayList<ViewPagerItem>?) {
    if(!array.isNullOrEmpty()){
        val viewpagerAdapter = ViewPagerAdapter(array)
        viewPager.adapter = viewpagerAdapter
    }
}

@BindingAdapter("bindViewPager2")
fun bindViewPager2(viewPager: ViewPager2, array: ArrayList<ViewPagerItem>?) {
    if(!array.isNullOrEmpty()){
        val viewpagerAdapter = ViewPager2Adapter(array)
        viewPager.adapter = viewpagerAdapter
    }
}

@BindingAdapter("loadGlideUrl")
fun loadGlideUrl(imageView: ImageView, url : String?) {
    if(!url.isNullOrEmpty()){
        Glide.with(imageView).load(url).into(imageView)
    }
}