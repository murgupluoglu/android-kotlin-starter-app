package com.murgupluoglu.kotlinmvvm.fragment.recyclerview

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide

enum class RecyclerViewItemTypes{
    ITEM_ONE,
    ITEM_VIEWPAGER
}
data class RecyclerViewItemModel(val viewType: Int, val id: String, val title: String, val subtitle: String, val body: String, val array: ArrayList<ViewPagerItem>?)
data class ViewPagerItem(val title: String, val imageURL: String)

@BindingAdapter("bindViewPager")
fun bindViewPager(viewPager: ViewPager, array: ArrayList<ViewPagerItem>?) {
    if(array != null){
        val viewpagerAdapter = ViewPagerAdapter(array)
        viewPager.adapter = viewpagerAdapter
    }
}

@BindingAdapter("loadGlideUrl")
fun loadGlideUrl(imageView: ImageView, url : String) {
    Glide.with(imageView).load(url).into(imageView)
}