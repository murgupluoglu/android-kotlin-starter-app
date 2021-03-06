package com.murgupluoglu.kotlinmvvm.fragment.people

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import com.murgupluoglu.kotlinmvvm.BR
import com.murgupluoglu.kotlinmvvm.R
import com.murgupluoglu.kotlinmvvm.databinding.FragmentPeopleBinding
import com.murgupluoglu.kotlinmvvm.databinding.ItemUserBinding
import com.murgupluoglu.kotlinmvvm.fragment.BaseFragment
import com.murgupluoglu.kotlinmvvm.model.*
import com.murgupluoglu.kotlinmvvm.utils.RESPONSE
import com.murgupluoglu.kotlinmvvm.utils.STATUS_ERROR
import com.murgupluoglu.kotlinmvvm.utils.STATUS_LOADING
import com.murgupluoglu.kotlinmvvm.utils.STATUS_SUCCESS
import io.paperdb.Paper
import kotlinx.android.synthetic.main.fragment_people.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Mustafa Urgupluoglu on 18.01.2019.
 */

class PeopleFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_people

    val peopleViewModel : PeopleViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (_binding as FragmentPeopleBinding).model = peopleViewModel

        peopleViewModel.peopleResponse.observe(this@PeopleFragment, Observer<RESPONSE<PeopleResponse>> { result ->
            when(result.status){
                STATUS_LOADING -> {
                    multipleStatusView.showLoading()
                }
                STATUS_SUCCESS -> {
                    multipleStatusView.showContent()

                    result.responseObject?.let {

                        if(!result.isFromCache){
                            Paper.book().write("PEOPLE_RESULT", it.results)
                        }

                        LastAdapter(it.results, BR.viewModel)
                                .map<User>(
                                        Type<ItemUserBinding>(R.layout.item_user)
                                                .onBind {}
                                )
                                .into(testRecylerView)
                    }


                }
                STATUS_ERROR -> {
                    multipleStatusView.showError()
                }
            }
        })

        peopleViewModel.getPeoples()
    }
}