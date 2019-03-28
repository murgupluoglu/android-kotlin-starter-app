package com.murgupluoglu.kotlinmvvm.fragment.people

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murgupluoglu.kotlinmvvm.di.koin.NetworkModule
import com.murgupluoglu.kotlinmvvm.model.*
import com.murgupluoglu.kotlinmvvm.utils.CacheListener
import com.murgupluoglu.kotlinmvvm.utils.RESPONSE
import com.murgupluoglu.kotlinmvvm.utils.request
import io.paperdb.Paper

/**
 * Created by Mustafa Urgupluoglu on 18.01.2019.
 */

class PeopleViewModel(val networkModule: NetworkModule) : ViewModel(), CacheListener {

    val peopleResponse: MutableLiveData<RESPONSE<PeopleResponse>> = MutableLiveData()

    var title = ObservableField<String>("Peoples Title")

    fun getPeoples(){
        peopleResponse.request(viewModelScope, networkModule.service().getPeoples(50), this)
    }

    override fun getCachedResponse(): Any {
        val peopleResult = Paper.book().read("PEOPLE_RESULT", ArrayList<User>())

        val peopleResponse = PeopleResponse()
        peopleResponse.results = peopleResult

        return peopleResponse
    }
}
