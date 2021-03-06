package com.murgupluoglu.kotlinmvvm.di.koin

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.murgupluoglu.kotlinmvvm.BuildConfig
import com.murgupluoglu.kotlinmvvm.service.ServiceInterface
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

/**
Created by mustafa.urgupluoglu on 6/27/18.
 */
class NetworkModule{

    companion object {
        var BASE_URL = "https://api.randomuser.me"
    }

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
                    .method(original.method(), original.body())
                    .build()

            // Customize or return the response
            chain.proceed(request)
        }

        val clt = httpClient.build()
        val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(clt)
                .build()

        return retrofit.create<ServiceInterface>(ServiceInterface::class.java)
    }
}
