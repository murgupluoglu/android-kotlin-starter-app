package com.murgupluoglu.kotlinmvvm

import android.app.Application
import com.blankj.utilcode.util.Utils


/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }
}