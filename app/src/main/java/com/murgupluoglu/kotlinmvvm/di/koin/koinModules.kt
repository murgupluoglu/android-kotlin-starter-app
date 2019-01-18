package com.murgupluoglu.kotlinmvvm.di.koin

import com.murgupluoglu.kotlinmvvm.fragment.people.PeopleViewModel
import com.murgupluoglu.kotlinmvvm.fragment.settings.SettingsViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

/**
Created by mustafa.urgupluoglu on 6/27/18.
 */
val myModule : Module = module {
    factory { MyRepository() }
    viewModel { SettingsViewModel(get()) }
    single { NetworkModule() }
    viewModel { PeopleViewModel(get()) }
}