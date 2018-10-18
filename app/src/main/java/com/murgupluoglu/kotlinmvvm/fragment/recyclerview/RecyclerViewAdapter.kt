package com.murgupluoglu.kotlinmvvm.fragment.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.murgupluoglu.kotlinmvvm.BR
import com.murgupluoglu.kotlinmvvm.R

class RecyclerViewAdapter(private val clickListener: RecyclerViewClickListener) : ListAdapter<RecyclerViewItemModel, RecyclerViewAdapter.MyViewHolder>(PlantDiffCallback()) {

    interface RecyclerViewClickListener {
        fun itemClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewDataBinding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        return MyViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
            bind(item, View.OnClickListener {
                clickListener.itemClicked(position)
            })
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        when (item.viewType) {
            RecyclerViewItemTypes.ITEM_ONE.ordinal -> return R.layout.item_one
            RecyclerViewItemTypes.ITEM_VIEWPAGER.ordinal -> return R.layout.item_two_viewpager
        }
        throw RuntimeException("invalid obj")
    }


    class MyViewHolder(private var viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(item: RecyclerViewItemModel, listener: View.OnClickListener) {
            viewDataBinding.apply {
                setVariable(BR.model, item)
                setVariable(BR.clickListener, listener)
                executePendingBindings()
            }
        }
    }
}

class PlantDiffCallback : DiffUtil.ItemCallback<RecyclerViewItemModel>() {
    override fun areItemsTheSame(oldItem: RecyclerViewItemModel, newItem: RecyclerViewItemModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RecyclerViewItemModel, newItem: RecyclerViewItemModel): Boolean {
        return oldItem == newItem
    }
}