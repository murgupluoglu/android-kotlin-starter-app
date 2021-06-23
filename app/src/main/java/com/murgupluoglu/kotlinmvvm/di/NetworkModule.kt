package com.murgupluoglu.kotlinmvvm.di

import com.murgupluoglu.kotlinmvvm.BuildConfig
import com.murgupluoglu.kotlinmvvm.api.ServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory


/*
*  Created by Mustafa Ürgüplüoğlu on 27.05.2021.
*  Copyright © 2021 Mustafa Ürgüplüoğlu. All rights reserved.
*/

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule{

    @Provides
    fun service(): ServiceInterface {
        val logging = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG){
            logging.level = HttpLoggingInterceptor.Level.BODY
        }else{
            logging.level = HttpLoggingInterceptor.Level.NONE
        }


        val httpClient = okhttp3.OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor { chain ->
            val original = chain.request()

            // Customize the request
            val request = original.newBuilder()
                .header("Content-Type", "application/json")
                .method(original.method, original.body)
                .build()

            // Customize or return the response
            chain.proceed(request)
        }

        val clt = httpClient.build()
        val retrofit = retrofit2.Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.randomuser.me")
            .client(clt)
            .build()

        return retrofit.create<ServiceInterface>(ServiceInterface::class.java)
    }
}