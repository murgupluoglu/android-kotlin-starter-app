package com.murgupluoglu.kotlinmvvm.service

import com.murgupluoglu.kotlinmvvm.model.GenericResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

interface ServiceInterface {

    @get:GET("/posts")
    val posts: Observable<List<GenericResponse>>
}