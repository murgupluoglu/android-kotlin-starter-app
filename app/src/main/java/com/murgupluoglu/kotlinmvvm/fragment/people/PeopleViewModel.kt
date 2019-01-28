package com.murgupluoglu.kotlinmvvm.fragment.people

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.murgupluoglu.kotlinmvvm.di.koin.NetworkModule
import com.murgupluoglu.kotlinmvvm.model.GenericResponse
import com.murgupluoglu.kotlinmvvm.model.PeopleResponse
import com.murgupluoglu.kotlinmvvm.model.User
import com.murgupluoglu.kotlinmvvm.model.requestGenericResponse
import io.paperdb.Paper
import kotlinx.coroutines.Job

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
        val peopleResult = Paper.book().read("PEOPLE_RESULT", ArrayList<User>())

        val peopleResponse = PeopleResponse()
        peopleResponse.results = peopleResult

        return peopleResponse
    }

    override fun onCleared(){
        job.cancel()
    }
}