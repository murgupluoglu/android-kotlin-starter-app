package com.murgupluoglu.kotlinmvvm.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.murgupluoglu.kotlinmvvm.di.koin.NetworkModule
import com.murgupluoglu.kotlinmvvm.fragment.home.HomeViewModel

class CustomViewModelFactory(private val networkModule: NetworkModule) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java))
            HomeViewModel(networkModule) as T
        else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}