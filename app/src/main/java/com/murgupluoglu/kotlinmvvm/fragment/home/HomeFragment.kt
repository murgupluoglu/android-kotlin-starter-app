package com.murgupluoglu.kotlinmvvm.fragment.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.murgupluoglu.kotlinmvvm.R
import com.murgupluoglu.kotlinmvvm.databinding.FragmentHomeBinding
import com.murgupluoglu.kotlinmvvm.di.koin.NetworkModule
import com.murgupluoglu.kotlinmvvm.fragment.BaseFragment
import com.murgupluoglu.kotlinmvvm.service.ServiceInterface
import com.murgupluoglu.kotlinmvvm.utils.CustomViewModelFactory
import com.murgupluoglu.kotlinmvvm.utils.log
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import javax.inject.Inject


/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

class HomeFragment : BaseFragment() {

//    @Inject
//    lateinit var serviceApi: ServiceInterface

    override val layoutId: Int = R.layout.fragment_home

    lateinit var viewModel: HomeViewModel
    lateinit var homeBinding: FragmentHomeBinding

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val networkModule : NetworkModule by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //AndroidSupportInjection.inject(this)
        homeBinding = binding as FragmentHomeBinding

        viewModel = ViewModelProviders.of(this, CustomViewModelFactory(networkModule)).get(HomeViewModel::class.java)
        lifecycle.addObserver(viewModel)

        homeBinding.model = viewModel


        compositeDisposable.add(
                networkModule.service().posts
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