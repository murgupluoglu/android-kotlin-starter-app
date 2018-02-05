package com.murgupluoglu.kotlinmvvm.activity

import android.app.Activity
import android.os.Bundle
import me.yokeyword.fragmentation.SupportActivity

/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

abstract class BaseActivity : SupportActivity() {

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