package com.murgupluoglu.kotlinmvvm.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    public override fun onResume() {
        super.onResume()
    }

    public override fun onDestroy() {
        super.onDestroy()
    }

    public override fun onStart() {
        super.onStart()
    }

    public override fun onStop() {
        super.onStop()
    }
}