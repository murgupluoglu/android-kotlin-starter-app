package com.murgupluoglu.kotlinmvvm.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.murgupluoglu.kotlinmvvm.R
import dagger.hilt.android.AndroidEntryPoint


/*
*  Created by Mustafa Ürgüplüoğlu on 26.05.2021.
*  Copyright © 2021 Mustafa Ürgüplüoğlu. All rights reserved.
*/

@AndroidEntryPoint
class SplashActivity :AppCompatActivity(R.layout.activity_splash) {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.test()
    }
}