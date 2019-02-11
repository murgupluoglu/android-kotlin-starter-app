package com.murgupluoglu.kotlinmvvm.fragment.recyclerview

enum class RecyclerViewItemTypes{
    ITEM_ONE,
    ITEM_VIEWPAGER
}
data class RecyclerViewItemModel(val viewType: Int, val id: String, val title: String, val subtitle: String, val body: String, val array: ArrayList<ViewPagerItem>?)
data class ViewPagerItem(val title: String, val imageURL: String)