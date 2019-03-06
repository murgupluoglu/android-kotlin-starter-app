package com.murgupluoglu.kotlinmvvm.fragment.people

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murgupluoglu.kotlinmvvm.di.koin.NetworkModule
import com.murgupluoglu.kotlinmvvm.model.PeopleResponse
import com.murgupluoglu.kotlinmvvm.model.RESPONSE
import com.murgupluoglu.kotlinmvvm.model.User
import com.murgupluoglu.kotlinmvvm.model.request
import io.paperdb.Paper

/**
 * Created by Mustafa Urgupluoglu on 18.01.2019.
 */

class PeopleViewModel(val networkModule: NetworkModule) : ViewModel(){

    val peopleResponse: MutableLiveData<RESPONSE<PeopleResponse>> = MutableLiveData()

    var title = ObservableField<String>("Peoples Title")

    fun getPeoples(){
        peopleResponse.request(viewModelScope, networkModule.service().getPeoples(50), returnFromCache = {getFromCache()})
    }

    fun getFromCache() : Any? {
        val peopleResult = Paper.book().read("PEOPLE_RESULT", ArrayList<User>())

        val peopleResponse = PeopleResponse()
        peopleResponse.results = peopleResult

        return peopleResponse
    }
}
