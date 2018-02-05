package com.murgupluoglu.kotlinmvvm

import android.util.Log
import com.murgupluoglu.kotlinmvvm.di.DaggerAppComponent
import dagger.android.support.DaggerApplication


/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

class App : DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        Log.e("DaggerExample", "Application onCreate")
    }
}