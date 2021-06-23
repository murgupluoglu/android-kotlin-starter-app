package com.murgupluoglu.kotlinmvvm.api

import com.murgupluoglu.kotlinmvvm.api.response.PeopleResponse
import retrofit2.http.GET
import retrofit2.http.Query

/*
*  Created by Mustafa Ürgüplüoğlu on 27.05.2021.
*  Copyright © 2021 Mustafa Ürgüplüoğlu. All rights reserved.
*/

interface ServiceInterface {

    @GET("/")
    suspend fun getPeoples(@Query("results") size: Int = 20): PeopleResponse

}