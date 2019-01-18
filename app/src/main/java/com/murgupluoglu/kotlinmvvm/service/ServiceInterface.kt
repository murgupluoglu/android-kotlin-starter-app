package com.murgupluoglu.kotlinmvvm.service

import com.murgupluoglu.kotlinmvvm.model.GenericResponse
import com.murgupluoglu.kotlinmvvm.model.PeopleResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

interface ServiceInterface {

    @GET("/posts")
    fun getPosts(): Deferred<List<GenericResponse>>

    @GET("/")
    fun getPeoples(): Deferred<PeopleResponse>

}