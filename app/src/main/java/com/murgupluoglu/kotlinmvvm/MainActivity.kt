package com.murgupluoglu.kotlinmvvm

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.murgupluoglu.kotlinmvvm.activity.BaseActivity
import com.murgupluoglu.kotlinmvvm.fragment.home.HomeFragment
import com.murgupluoglu.kotlinmvvm.fragment.settings.SettingsFragment
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                loadRootFragment(R.id.content, HomeFragment.newInstance(), true, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                loadRootFragment(R.id.content, SettingsFragment.newInstance(), true, false)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        if (findFragment(HomeFragment::class.java) == null) {
            loadRootFragment(R.id.content, HomeFragment.newInstance())
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

}