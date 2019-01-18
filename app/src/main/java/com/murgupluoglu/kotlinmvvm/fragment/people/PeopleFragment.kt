package com.murgupluoglu.kotlinmvvm.fragment.people

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import com.murgupluoglu.kotlinmvvm.BR
import com.murgupluoglu.kotlinmvvm.R
import com.murgupluoglu.kotlinmvvm.databinding.ItemUserBinding
import com.murgupluoglu.kotlinmvvm.fragment.BaseFragment
import com.murgupluoglu.kotlinmvvm.model.*
import kotlinx.android.synthetic.main.fragment_people.*
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
        peopleViewModel.peopleResponse.observe(this@PeopleFragment, Observer<GenericResponse> { result ->
            LogUtils.e("RESULT $result")
            when(result.status){
                STATUS_LOADING -> {
                    multipleStatusView.showLoading()
                }
                STATUS_SUCCESS -> {
                    multipleStatusView.showContent()

                    val peopleResponse = result.responseObject as PeopleResponse

                    val boxStore = MyObjectBox.builder().androidContext(Utils.getApp()).build()
                    val userBox = boxStore.boxFor(User::class.java)
                    userBox.put(peopleResponse.results)

                    LastAdapter(peopleResponse.results, BR.viewModel)
                            .map<User>(
                                    Type<ItemUserBinding>(R.layout.item_user)
                                            .onBind {}
                            )
                            .into(testRecylerView)

                }
                STATUS_ERROR -> {
                    multipleStatusView.showError()
                }
            }
        })

        peopleViewModel.getPeoples()
    }
}