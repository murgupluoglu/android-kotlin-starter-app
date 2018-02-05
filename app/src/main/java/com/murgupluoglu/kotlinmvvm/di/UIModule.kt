package com.murgupluoglu.kotlinmvvm.di

import com.murgupluoglu.kotlinmvvm.MainActivity
import com.murgupluoglu.kotlinmvvm.fragment.home.HomeFragment
import com.murgupluoglu.kotlinmvvm.fragment.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

@Module
internal abstract class UiModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun contributeSettingsFragment(): SettingsFragment

}