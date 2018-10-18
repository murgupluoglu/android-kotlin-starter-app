package com.murgupluoglu.kotlinmvvm

import android.os.Bundle
import androidx.navigation.Navigation
import com.murgupluoglu.kotlinmvvm.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //AndroidInjection.inject(this)

        val navController = Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment)
        bottomNavigation.setOnNavigationItemSelectedListener { p0 ->
            when(p0.itemId){
                R.id.homeFragment -> navController.navigate(R.id.homeFragment)
                R.id.settingsFragment -> navController.navigate(R.id.settingsFragment)
                R.id.recyclerViewFragment -> navController.navigate(R.id.recyclerViewFragment)
            }

            true
        }
        //NavigationUI.setupWithNavController(bottomNavigation, navController)
    }

}