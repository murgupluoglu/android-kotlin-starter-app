package com.murgupluoglu.kotlinmvvm.fragment.people

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.murgupluoglu.kotlinmvvm.di.koin.NetworkModule
import com.murgupluoglu.kotlinmvvm.model.GenericResponse
import kotlinx.coroutines.*
import retrofit2.HttpException

/**
 * Created by Mustafa Urgupluoglu on 18.01.2019.
 */

class PeopleViewModel(val networkModule: NetworkModule) : ViewModel(){

    val userResponse: MutableLiveData<GenericResponse> = MutableLiveData()

    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    fun getPeoples(){
        scope.launch {

            val apiResponse = GenericResponse()

            try {
                val result = networkModule.service().getPeoples().await()
                apiResponse.responseObject = result.results
            } catch (httpE: HttpException) {
                apiResponse.hasError = true
                apiResponse.errorMessage = httpE.message()
                apiResponse.errorCode = httpE.code()
            }

            userResponse.value = apiResponse
        }
    }

    override fun onCleared(){
        job.cancel()
    }
}