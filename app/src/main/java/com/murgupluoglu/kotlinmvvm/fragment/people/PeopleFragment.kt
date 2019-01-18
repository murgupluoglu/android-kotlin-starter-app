package com.murgupluoglu.kotlinmvvm.fragment.people

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.murgupluoglu.kotlinmvvm.R
import com.murgupluoglu.kotlinmvvm.fragment.BaseFragment
import com.murgupluoglu.kotlinmvvm.model.GenericResponse
import com.murgupluoglu.kotlinmvvm.model.User
import org.koin.android.ext.android.inject

/**
 * Created by Mustafa Urgupluoglu on 18.01.2019.
 */

class PeopleFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_people

    val peopleViewModel : PeopleViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        @Suppress("UNCHECKED_CAST")
        peopleViewModel.userResponse.observe(this@PeopleFragment, Observer<GenericResponse> { result ->
            if(result.hasError){
                //Error
            }else{
                val users = result.responseObject as List<User>
                users.forEach {
                    println(it)
                }
            }
        })

        peopleViewModel.getPeoples()
    }
}