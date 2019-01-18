package com.murgupluoglu.kotlinmvvm.model

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * Created by Mustafa Urgupluoglu on 18.01.2019.
 */

const val STATUS_LOADING = 1
const val STATUS_SUCCESS = 2
const val STATUS_ERROR = 3

data class GenericResponse(
        var status: Int = STATUS_LOADING,
        var errorCode: Int = -1,
        var errorMessage: String = "",
        var isFromCache: Boolean = false,
        var responseObject: Any = -1
)

fun requestGenericResponse(deferred: Deferred<*>, liveData: MutableLiveData<*>, returnFromCache: () -> Any?): Job {
    val job = Job()
    val scope = CoroutineScope(job + Dispatchers.Main)

    scope.launch {

        val response = GenericResponse()
        response.status = STATUS_LOADING
        liveData.value = response

        val cacheValue = returnFromCache()
        if(cacheValue != null){
            response.status = STATUS_SUCCESS
            response.isFromCache = true
            response.responseObject = cacheValue
            liveData.value = response
        }

        response.isFromCache = false
        try {
            val result = deferred.await()
            response.status = STATUS_SUCCESS
            response.responseObject = result!!
        } catch (httpE: HttpException) {
            response.status = STATUS_ERROR
            response.errorMessage = httpE.message()
            response.errorCode = httpE.code()
        } catch (unknownHostE: UnknownHostException) {
            response.status = STATUS_ERROR
            response.errorMessage = unknownHostE.message.toString()
        }

        liveData.value = response
    }

    return job
}