package com.murgupluoglu.kotlinmvvm.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.murgupluoglu.kotlinmvvm.di.koin.NetworkModule
import com.murgupluoglu.kotlinmvvm.fragment.home.HomeViewModel
import com.murgupluoglu.kotlinmvvm.fragment.recyclerview.RecyclerViewModel


class CustomViewModelFactory(private val networkModule: NetworkModule) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java))
            HomeViewModel(networkModule) as T
        else if (modelClass.isAssignableFrom(RecyclerViewModel::class.java))
            RecyclerViewModel(networkModule) as T
        else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}