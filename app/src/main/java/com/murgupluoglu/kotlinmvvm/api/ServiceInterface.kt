package com.murgupluoglu.kotlinmvvm.api

import com.murgupluoglu.kotlinmvvm.api.response.ProductModel
import retrofit2.http.GET
import retrofit2.http.Path

/*
*  Created by Mustafa Ürgüplüoğlu on 27.05.2021.
*  Copyright © 2021 Mustafa Ürgüplüoğlu. All rights reserved.
*/

interface ServiceInterface {

    @GET("/products")
    suspend fun getProducts(): List<ProductModel>

    @GET("/products/{productId}")
    suspend fun getProduct(@Path(value = "productId", encoded = true) userId: String): ProductModel

}