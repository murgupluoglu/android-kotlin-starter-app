package com.murgupluoglu.kotlinmvvm.activity.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.murgupluoglu.kotlinmvvm.api.response.ProductModel
import com.murgupluoglu.kotlinmvvm.databinding.ItemProductBinding

internal class ProductAdapter(
    var products: MutableList<ProductModel>,
    private val clickedListener: (product: ProductModel) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = products.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(
            ItemProductBinding.inflate(inflater, parent, false),
            clickedListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProductViewHolder -> holder.bind(products[position])
        }
    }
}