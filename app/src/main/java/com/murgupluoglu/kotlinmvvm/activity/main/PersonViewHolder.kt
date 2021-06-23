package com.murgupluoglu.kotlinmvvm.activity.main

import androidx.recyclerview.widget.RecyclerView
import com.murgupluoglu.kotlinmvvm.api.response.PersonModel
import com.murgupluoglu.kotlinmvvm.databinding.ItemPersonBinding

internal class PersonViewHolder(
    private val binding: ItemPersonBinding,
    private val clickedListener: (person: PersonModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(person: PersonModel) {
        binding.apply {
            root.setOnClickListener {
                clickedListener.invoke(person)
            }
            nameTextView.text = "${person.name.title} ${person.name.first} ${person.name.last}"
        }
    }
}