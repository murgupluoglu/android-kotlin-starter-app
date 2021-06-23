package com.murgupluoglu.kotlinmvvm.activity.main

import androidx.recyclerview.widget.RecyclerView
import com.murgupluoglu.kotlinmvvm.api.response.ProductModel
import com.murgupluoglu.kotlinmvvm.databinding.ItemProductBinding

internal class ProductViewHolder(
    private val binding: ItemProductBinding,
    private val clickedListener: (product: ProductModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductModel) {
        binding.apply {
            root.setOnClickListener {
                clickedListener.invoke(product)
            }
            nameTextView.text = "${product.title}"
        }
    }
}