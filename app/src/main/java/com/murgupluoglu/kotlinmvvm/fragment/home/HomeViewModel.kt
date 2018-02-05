package com.murgupluoglu.kotlinmvvm.fragment.home

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.databinding.BaseObservable
import com.murgupluoglu.kotlinmvvm.utils.Utils

/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

class HomeViewModel : BaseObservable(), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected fun start() {
        Utils.log("start")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected fun stop() {
        Utils.log("stop")
    }
}