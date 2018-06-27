package com.murgupluoglu.kotlinmvvm.fragment.settings

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.murgupluoglu.kotlinmvvm.di.koin.NetworkModule
import com.murgupluoglu.kotlinmvvm.model.GenericResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

class SettingsViewModel(val networkModule: NetworkModule) : ViewModel(){

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val genericResponsList: MutableLiveData<List<GenericResponse>> = MutableLiveData()

    fun getServerData(){
        compositeDisposable.add(
                networkModule.service().posts
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { response ->
                            genericResponsList.value = response
                        }
        )
    }

    override fun onCleared(){
        compositeDisposable.clear()
    }
}