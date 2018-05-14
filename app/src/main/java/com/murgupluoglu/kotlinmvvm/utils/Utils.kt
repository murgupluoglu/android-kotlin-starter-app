package com.murgupluoglu.kotlinmvvm.utils

import android.util.Log
import com.murgupluoglu.kotlinmvvm.BuildConfig

/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

fun String.log() {
    if (BuildConfig.DEBUG) {
        Log.e("DEBUG_LOG: ", this)
    }
}