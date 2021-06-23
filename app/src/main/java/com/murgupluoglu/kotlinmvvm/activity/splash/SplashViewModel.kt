package com.murgupluoglu.kotlinmvvm.activity.splash

import androidx.lifecycle.ViewModel
import com.murgupluoglu.kotlinmvvm.api.ServiceInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/*
*  Created by Mustafa Ürgüplüoğlu on 27.05.2021.
*  Copyright © 2021 Mustafa Ürgüplüoğlu. All rights reserved.
*/

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val service: ServiceInterface
) : ViewModel() {

    fun test() {
        println(service)
    }
}