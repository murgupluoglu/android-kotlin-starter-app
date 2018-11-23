package com.murgupluoglu.kotlinmvvm.fragment.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.murgupluoglu.kotlinmvvm.di.koin.NetworkModule
import com.murgupluoglu.kotlinmvvm.model.GenericResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

class SettingsViewModel(val networkModule: NetworkModule) : ViewModel(){

    val genericResponsList: MutableLiveData<List<GenericResponse>> = MutableLiveData()

    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    fun getServerData(){
        scope.launch {
            val result = networkModule.service().getPosts().await()

            genericResponsList.value = result
        }
    }

    override fun onCleared(){
        job.cancel()
    }
}