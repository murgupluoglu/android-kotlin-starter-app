package com.murgupluoglu.kotlinmvvm.fragment.people

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.Utils
import com.murgupluoglu.kotlinmvvm.di.koin.NetworkModule
import com.murgupluoglu.kotlinmvvm.model.*
import kotlinx.coroutines.*

/**
 * Created by Mustafa Urgupluoglu on 18.01.2019.
 */

class PeopleViewModel(val networkModule: NetworkModule) : ViewModel(){

    val peopleResponse: MutableLiveData<GenericResponse> = MutableLiveData()
    var job = Job()


    fun getPeoples(){
        job = requestGenericResponse(networkModule.service().getPeoples(50), peopleResponse, returnFromCache = {getFromCache()})
    }

    fun getFromCache() : Any? {
        val boxStore = MyObjectBox.builder().androidContext(Utils.getApp()).build()
        val userBox = boxStore.boxFor(User::class.java)
        val users = userBox.all

        val peopleResponse = PeopleResponse()
        peopleResponse.results = users

        return peopleResponse
    }

    override fun onCleared(){
        job.cancel()
    }
}