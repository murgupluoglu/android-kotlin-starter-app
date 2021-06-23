package com.murgupluoglu.kotlinmvvm

import android.app.Application
import com.blankj.utilcode.util.Utils
import dagger.hilt.android.HiltAndroidApp


/*
*  Created by Mustafa Ürgüplüoğlu on 27.05.2021.
*  Copyright © 2021 Mustafa Ürgüplüoğlu. All rights reserved.
*/

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }
}