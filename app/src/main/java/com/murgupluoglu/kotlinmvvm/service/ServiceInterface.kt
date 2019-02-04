package com.murgupluoglu.kotlinmvvm.service

import com.murgupluoglu.kotlinmvvm.model.JsonPlaceHolderResponse
import com.murgupluoglu.kotlinmvvm.model.PeopleResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

interface ServiceInterface {

    @GET
    fun getPosts(@Url url : String): Deferred<List<JsonPlaceHolderResponse>>

    @GET("/")
    fun getPeoples(@Query("results") size : Int): Deferred<PeopleResponse>

}