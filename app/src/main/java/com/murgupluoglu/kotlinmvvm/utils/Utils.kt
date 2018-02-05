package com.murgupluoglu.kotlinmvvm.utils

import android.util.Log
import com.murgupluoglu.kotlinmvvm.BuildConfig

/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

object Utils {
    fun log(msg: String?) {
        if (BuildConfig.DEBUG) {
            if (msg != null)
                Log.e("SPEED_LOG: ", msg)
        }
    }
}