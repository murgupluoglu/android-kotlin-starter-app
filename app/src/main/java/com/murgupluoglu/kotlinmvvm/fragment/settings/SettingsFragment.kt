package com.murgupluoglu.kotlinmvvm.fragment.settings

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.murgupluoglu.kotlinmvvm.R
import com.murgupluoglu.kotlinmvvm.databinding.FragmentSettingsBinding
import com.murgupluoglu.kotlinmvvm.di.koin.MyRepository
import com.murgupluoglu.kotlinmvvm.fragment.BaseFragment
import com.murgupluoglu.kotlinmvvm.model.GenericResponse
import com.murgupluoglu.kotlinmvvm.utils.log
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

class SettingsFragment : BaseFragment() {

    override val layoutId: Int get() = R.layout.fragment_settings

    //lateinit var bind: FragmentSettingsBinding

    val presenter : MyRepository by inject()

    val settingsViewModel : SettingsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bind = binding as FragmentSettingsBinding

        presenter.giveHello().log()

        settingsViewModel.genericResponsList.observe(this@SettingsFragment, Observer<List<GenericResponse>> { result ->
            result!!.forEachIndexed { index, one ->
                one.toString().log()
            }
        })

        settingsViewModel.getServerData()

    }
}