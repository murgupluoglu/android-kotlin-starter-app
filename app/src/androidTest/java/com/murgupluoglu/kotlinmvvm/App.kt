package com.murgupluoglu.kotlinmvvm

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.murgupluoglu.kotlinmvvm.utils.log
import io.paperdb.Paper

/**
 * Created by Mustafa Urgupluoglu on 5.02.2019.
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        "Application TEST onCreate".log()
        Utils.init(this)
        Paper.init(this)
    }
}