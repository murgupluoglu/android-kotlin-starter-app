package com.murgupluoglu.kotlinmvvm.fragment.recyclerview

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.murgupluoglu.kotlinmvvm.R
import com.murgupluoglu.kotlinmvvm.databinding.FragmentRecyclerviewBinding
import com.murgupluoglu.kotlinmvvm.di.koin.NetworkModule
import com.murgupluoglu.kotlinmvvm.fragment.BaseFragment
import com.murgupluoglu.kotlinmvvm.utils.CustomViewModelFactory
import kotlinx.android.synthetic.main.fragment_recyclerview.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject


class RecyclerViewFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_recyclerview

    lateinit var viewModel: RecyclerViewModel
    lateinit var fragmnetBinding: FragmentRecyclerviewBinding

    val networkModule: NetworkModule by inject()

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    val items: ArrayList<RecyclerViewItemModel> = arrayListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmnetBinding = binding as FragmentRecyclerviewBinding

        viewModel = ViewModelProviders.of(this, CustomViewModelFactory(networkModule)).get(RecyclerViewModel::class.java)
        lifecycle.addObserver(viewModel)

        fragmnetBinding.model = viewModel

        testRecylerView.layoutManager = LinearLayoutManager(context)
        recyclerViewAdapter = RecyclerViewAdapter(object : RecyclerViewAdapter.RecyclerViewClickListener {
            override fun itemClicked(position: Int) {
                Log.d("RecyclerViewAdapter", "clicked: " + position)
            }
        })
        testRecylerView.adapter = recyclerViewAdapter

        val array: ArrayList<ViewPagerItem> = arrayListOf()

        val item = ViewPagerItem(
                "title",
                "http://www.tompetty.com/sites/g/files/g2000007521/f/styles/photo-carousel/public/sample001.jpg")

        array.add(item)
        array.add(item)
        items.add(RecyclerViewItemModel(RecyclerViewItemTypes.ITEM_VIEWPAGER.ordinal, "", "", "", "", array))
        items.add(RecyclerViewItemModel(RecyclerViewItemTypes.ITEM_VIEWPAGER.ordinal, "", "", "", "", array))

        val myJob = CoroutineScope(Dispatchers.IO).launch {
            val result = networkModule.service().getPosts().await()

            withContext(Dispatchers.Main) {
                result.forEach {
                    val model = RecyclerViewItemModel(
                            RecyclerViewItemTypes.ITEM_ONE.ordinal,
                            it.id,
                            it.title,
                            it.title.reversed(),
                            it.body,
                            null)
                    items.add(model)
                }
                items.add(RecyclerViewItemModel(RecyclerViewItemTypes.ITEM_VIEWPAGER.ordinal, "", "", "", "", array))
                recyclerViewAdapter.submitList(items)
            }
        }
    }
}