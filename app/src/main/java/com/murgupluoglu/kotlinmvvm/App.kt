package com.murgupluoglu.kotlinmvvm

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.murgupluoglu.kotlinmvvm.di.koin.myModules
import com.murgupluoglu.kotlinmvvm.utils.log
import io.paperdb.Paper
import org.koin.standalone.StandAloneContext.startKoin


/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

class App : Application() {

//    override fun applicationInjector() = DaggerAppComponent.builder()
//            .application(this)
//            .build()

    override fun onCreate() {
        super.onCreate()
        "Application onCreate".log()
        startKoin(listOf(myModules))
        Utils.init(this)
        Paper.init(this)
    }
}