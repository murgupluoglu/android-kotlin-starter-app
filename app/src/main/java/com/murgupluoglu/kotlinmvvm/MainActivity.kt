package com.murgupluoglu.kotlinmvvm

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.murgupluoglu.kotlinmvvm.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //AndroidInjection.inject(this)

        val navController = Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }

}