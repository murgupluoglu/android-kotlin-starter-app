package com.murgupluoglu.kotlinmvvm.fragment.recyclerview

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.murgupluoglu.kotlinmvvm.R
import com.murgupluoglu.kotlinmvvm.databinding.FragmentRecyclerviewBinding
import com.murgupluoglu.kotlinmvvm.di.koin.NetworkModule
import com.murgupluoglu.kotlinmvvm.fragment.BaseFragment
import com.murgupluoglu.kotlinmvvm.utils.CustomViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_recyclerview.*
import org.koin.android.ext.android.inject
import java.util.ArrayList

class RecyclerViewFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_recyclerview

    lateinit var viewModel: RecyclerViewModel
    lateinit var fragmnetBinding: FragmentRecyclerviewBinding

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val networkModule : NetworkModule by inject()

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

        val array : ArrayList<ViewPagerItem> = arrayListOf()

        val item = ViewPagerItem(
                "title",
                "http://www.tompetty.com/sites/g/files/g2000007521/f/styles/photo-carousel/public/sample001.jpg")

        array.add(item)
        array.add(item)
        items.add(RecyclerViewItemModel(RecyclerViewItemTypes.ITEM_VIEWPAGER.ordinal, "", "", "", "", array))
        items.add(RecyclerViewItemModel(RecyclerViewItemTypes.ITEM_VIEWPAGER.ordinal, "", "", "", "", array))

        compositeDisposable.add(
                networkModule.service().posts
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { response ->
                            response.forEachIndexed { index, genericResponse ->
                                val item = RecyclerViewItemModel(
                                        RecyclerViewItemTypes.ITEM_ONE.ordinal,
                                        genericResponse.id,
                                        genericResponse.title,
                                        genericResponse.title.reversed(),
                                        genericResponse.body,
                                        null)
                                items.add(item)
                            }
                            items.add(RecyclerViewItemModel(RecyclerViewItemTypes.ITEM_VIEWPAGER.ordinal, "", "", "", "", array))
                            recyclerViewAdapter.submitList(items)
                        }
        )
    }
}