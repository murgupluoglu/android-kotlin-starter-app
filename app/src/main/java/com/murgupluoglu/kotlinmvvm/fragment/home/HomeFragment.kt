package com.murgupluoglu.kotlinmvvm.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.View
import com.murgupluoglu.kotlinmvvm.R
import com.murgupluoglu.kotlinmvvm.databinding.FragmentHomeBinding
import com.murgupluoglu.kotlinmvvm.fragment.BaseFragment
import com.murgupluoglu.kotlinmvvm.service.ServiceInterface
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.yokeyword.fragmentation.ISupportFragment
import javax.inject.Inject


/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var serviceApi: ServiceInterface

    override val layout: Int = R.layout.fragment_home

    lateinit var viewModel: HomeViewModel
    lateinit var homeBinding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)
        homeBinding = binding as FragmentHomeBinding

        viewModel = HomeViewModel()
        getLifecycle().addObserver(viewModel)

        homeBinding.setModel(viewModel)


        serviceApi.posts
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->

                    response.forEach { item -> Log.e("Title: ", item.title)}

                })
    }

    companion object {

        fun newInstance(): ISupportFragment {

            /*
            Bundle args = new Bundle();
            args.putString(Constants.PARAM_SCREEN, screen.toString());
            fragment.setArguments(args);
            */

            return HomeFragment()
        }
    }
}