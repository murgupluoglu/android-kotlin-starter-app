package com.murgupluoglu.kotlinmvvm.fragment.recyclerview

import android.support.constraint.ConstraintLayout
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.murgupluoglu.kotlinmvvm.databinding.ItemViewpagerBinding

class ViewPagerAdapter(val items: ArrayList<ViewPagerItem>) : PagerAdapter() {

    override fun getCount(): Int = items.size

    override fun isViewFromObject(view: View, anyObject: Any): Boolean {
        return view == anyObject
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ItemViewpagerBinding.inflate(LayoutInflater.from(container.context), container, false)

        binding.apply {
            val test = items.get(position)
            itemviewpager = test
        }

        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, anyObject: Any) {
        container.removeView(anyObject as ConstraintLayout)
    }
}