package com.murgupluoglu.kotlinmvvm.fragment.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.murgupluoglu.kotlinmvvm.R
import com.murgupluoglu.kotlinmvvm.databinding.FragmentHomeBinding
import com.murgupluoglu.kotlinmvvm.di.koin.NetworkModule
import com.murgupluoglu.kotlinmvvm.fragment.BaseFragment
import com.murgupluoglu.kotlinmvvm.utils.CustomViewModelFactory
import com.murgupluoglu.kotlinmvvm.utils.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject


/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

class HomeFragment : BaseFragment() {

//    @Inject
//    lateinit var serviceApi: ServiceInterface

    override val layoutId: Int = R.layout.fragment_home

    lateinit var viewModel: HomeViewModel
    lateinit var homeBinding: FragmentHomeBinding

    val networkModule : NetworkModule by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //AndroidSupportInjection.inject(this)
        homeBinding = binding as FragmentHomeBinding

        viewModel = ViewModelProviders.of(this, CustomViewModelFactory(networkModule)).get(HomeViewModel::class.java)
        lifecycle.addObserver(viewModel)

        homeBinding.model = viewModel


        val myJob = CoroutineScope(Dispatchers.IO).launch {
            val result = networkModule.service().getPosts().await()

            withContext(Dispatchers.Main) {
                result.forEach { item ->
                    ("Title: " + item.title).log()
                }
            }
        }
    }
}