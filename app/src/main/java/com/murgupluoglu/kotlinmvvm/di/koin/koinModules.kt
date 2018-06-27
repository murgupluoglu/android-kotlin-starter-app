package com.murgupluoglu.kotlinmvvm.di.koin

import com.murgupluoglu.kotlinmvvm.fragment.settings.SettingsViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

/**
Created by mustafa.urgupluoglu on 6/27/18.
 */
val myModule : Module = applicationContext {
    factory { MyRepository() }
    viewModel { SettingsViewModel(get()) }
    bean { NetworkModule() }
}