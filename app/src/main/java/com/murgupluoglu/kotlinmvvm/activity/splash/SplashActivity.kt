package com.murgupluoglu.kotlinmvvm.activity.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ActivityUtils
import com.murgupluoglu.kotlinmvvm.R
import com.murgupluoglu.kotlinmvvm.activity.main.MainActivity
import com.murgupluoglu.kotlinmvvm.databinding.ActivitySplashBinding
import com.murgupluoglu.kotlinmvvm.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit


/*
*  Created by Mustafa Ürgüplüoğlu on 26.05.2021.
*  Copyright © 2021 Mustafa Ürgüplüoğlu. All rights reserved.
*/

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySplashBinding::inflate)

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(1))
            withContext(Dispatchers.Main) {
                ActivityUtils.startActivity(MainActivity::class.java)
                finish()
            }
        }
    }
}