package com.murgupluoglu.kotlinmvvm.fragment.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.murgupluoglu.kotlinmvvm.R

/**
 * Created by Mustafa Urgupluoglu on 11.02.2019.
 */

class ViewPager2Adapter(private val items: ArrayList<ViewPagerItem>) : RecyclerView.Adapter<ViewPager2ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPager2ItemHolder {
        return ViewPager2ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_viewpager, parent, false))
    }

    override fun onBindViewHolder(holder: ViewPager2ItemHolder, position: Int) {
        holder.textViewTitle.text = "ViewPager2 ${items[position].title}"
        //loadGlideUrl(holder.imageView, items[position].imageURL)
    }

    override fun getItemCount() = items.size
}

class ViewPager2ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textViewTitle: TextView = view.findViewById(R.id.textViewTitle)
    val imageView: ImageView = view.findViewById(R.id.imageView)
}