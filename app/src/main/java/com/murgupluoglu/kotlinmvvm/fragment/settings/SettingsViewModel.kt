package com.murgupluoglu.kotlinmvvm.fragment.settings

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murgupluoglu.kotlinmvvm.di.koin.NetworkModule
import com.murgupluoglu.kotlinmvvm.model.JsonPlaceHolderResponse
import com.murgupluoglu.kotlinmvvm.utils.RESPONSE
import com.murgupluoglu.kotlinmvvm.utils.request

/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

class SettingsViewModel(val networkModule: NetworkModule) : ViewModel(){

    var title = ObservableField<String>("Settings")

    val genericResponsList : MutableLiveData<RESPONSE<List<JsonPlaceHolderResponse>>> = MutableLiveData()

    fun getServerData(){
        genericResponsList.request(viewModelScope, networkModule.service().getPosts("https://jsonplaceholder.typicode.com/posts"))
    }
}