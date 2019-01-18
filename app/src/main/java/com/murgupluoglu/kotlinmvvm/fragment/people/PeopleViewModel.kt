package com.murgupluoglu.kotlinmvvm.fragment.people

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.murgupluoglu.kotlinmvvm.di.koin.NetworkModule
import com.murgupluoglu.kotlinmvvm.model.*
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * Created by Mustafa Urgupluoglu on 18.01.2019.
 */

class UserViewModel(var imageUrl : String, var name : String, var phone : String)

class PeopleViewModel(val networkModule: NetworkModule) : ViewModel(){

    val peopleResponse: MutableLiveData<GenericResponse> = MutableLiveData()
    var job = Job()


    fun getPeoples(){
        job = requestGenericResponse(networkModule.service().getPeoples(50), peopleResponse)
    }

    override fun onCleared(){
        job.cancel()
    }
}