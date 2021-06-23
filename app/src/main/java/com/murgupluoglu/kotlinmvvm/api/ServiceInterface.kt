package com.murgupluoglu.kotlinmvvm.api

import com.murgupluoglu.kotlinmvvm.api.response.JsonPlaceHolderResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


/*
*  Created by Mustafa Ürgüplüoğlu on 27.05.2021.
*  Copyright © 2021 Mustafa Ürgüplüoğlu. All rights reserved.
*/

interface ServiceInterface {

    @GET
    suspend fun getPosts(@Url url : String): List<JsonPlaceHolderResponse>

    @GET("/")
    suspend fun getPeoples(@Query("results") size : Int): ResponseBody

}