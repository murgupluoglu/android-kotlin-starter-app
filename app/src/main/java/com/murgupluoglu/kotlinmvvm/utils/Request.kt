package com.murgupluoglu.kotlinmvvm.utils

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

data class RESPONSE<T>(
        var status: Int = STATUS_LOADING,
        var errorCode: Int = -1,
        var errorMessage: String = "",
        var isFromCache: Boolean = false,
        var responseObject: T? = null
)

interface CacheListener{
    fun getCachedResponse() : Any
}

@Suppress("UNCHECKED_CAST")
fun <T> MutableLiveData<RESPONSE<T>>.request(viewModelScope : CoroutineScope, deferred: Deferred<*>, cacheListener: CacheListener? = null) {

    viewModelScope.launch {

        val response = RESPONSE<T>()
        response.status = STATUS_LOADING
        this@request.value = response

        if(cacheListener != null){
            val cacheValue = cacheListener.getCachedResponse()
            response.status = STATUS_SUCCESS
            response.isFromCache = true
            response.responseObject =  cacheValue as T
            this@request.value = response
        }


        response.isFromCache = false
        try {
            val result = deferred.await()
            response.status = STATUS_SUCCESS
            response.responseObject = result as T
        } catch (httpE: HttpException) {
            response.status = STATUS_ERROR
            response.errorMessage = httpE.message()
            response.errorCode = httpE.code()
        } catch (unknownHostE: UnknownHostException) {
            response.status = STATUS_ERROR
            response.errorMessage = unknownHostE.message.toString()
        } catch (e : Exception){
            response.status = STATUS_ERROR
            response.errorMessage = e.message.toString()
        }

        this@request.value = response
    }
}

fun <T> MutableLiveData<RESPONSE<T>>.request(deferred: Deferred<*>, cacheListener: CacheListener? = null): Job {

    val job = Job()
    val scope = CoroutineScope(job + Dispatchers.Main)

    request(scope, deferred, cacheListener)

    return job
}