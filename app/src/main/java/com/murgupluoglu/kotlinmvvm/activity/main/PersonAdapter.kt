package com.murgupluoglu.kotlinmvvm.activity.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.murgupluoglu.kotlinmvvm.api.response.PersonModel
import com.murgupluoglu.kotlinmvvm.databinding.ItemPersonBinding

internal class PersonAdapter(
    var peoples: MutableList<PersonModel>,
    private val clickedListener: (person: PersonModel) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = peoples.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PersonViewHolder(
            ItemPersonBinding.inflate(inflater, parent, false),
            clickedListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PersonViewHolder -> holder.bind(peoples[position])
        }
    }
}