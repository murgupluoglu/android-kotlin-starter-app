package com.murgupluoglu.kotlinmvvm

import com.murgupluoglu.kotlinmvvm.di.DaggerAppComponent
import com.murgupluoglu.kotlinmvvm.di.koin.myModule
import com.murgupluoglu.kotlinmvvm.utils.log
import dagger.android.support.DaggerApplication
import org.koin.android.ext.android.startKoin


/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

class App : DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        "Application onCreate".log()
        startKoin(this, listOf(myModule))
    }
}