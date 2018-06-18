package com.murgupluoglu.kotlinmvvm.fragment.home

import android.os.Bundle
import android.view.View
import com.murgupluoglu.kotlinmvvm.R
import com.murgupluoglu.kotlinmvvm.databinding.FragmentHomeBinding
import com.murgupluoglu.kotlinmvvm.fragment.BaseFragment
import com.murgupluoglu.kotlinmvvm.service.ServiceInterface
import com.murgupluoglu.kotlinmvvm.utils.log
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var serviceApi: ServiceInterface

    override val layoutId: Int = R.layout.fragment_home

    lateinit var viewModel: HomeViewModel
    lateinit var homeBinding: FragmentHomeBinding

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)
        homeBinding = binding as FragmentHomeBinding

        viewModel = HomeViewModel()
        getLifecycle().addObserver(viewModel)

        homeBinding.setModel(viewModel)


        compositeDisposable.add(
                serviceApi.posts
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->

                    response.forEach {
                        item -> ("Title: " + item.title).log()
                    }

                }
        )
    }
}