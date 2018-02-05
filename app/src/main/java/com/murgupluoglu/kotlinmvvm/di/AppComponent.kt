package com.murgupluoglu.kotlinmvvm.di

import com.murgupluoglu.kotlinmvvm.App
import com.murgupluoglu.kotlinmvvm.MainActivity
import com.murgupluoglu.kotlinmvvm.fragment.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    NetworkModule::class,
    UiModule::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}