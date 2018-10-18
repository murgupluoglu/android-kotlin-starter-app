package com.murgupluoglu.kotlinmvvm.fragment.home

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.murgupluoglu.kotlinmvvm.di.koin.NetworkModule
import com.murgupluoglu.kotlinmvvm.utils.log


/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

class HomeViewModel(val networkModule: NetworkModule) : ViewModel(), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected fun start() {
        "start".log()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected fun stop() {
        "stop".log()
    }
}