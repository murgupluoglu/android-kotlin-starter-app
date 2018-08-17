package com.murgupluoglu.kotlinmvvm.di.koin

import com.murgupluoglu.kotlinmvvm.service.ServiceInterface
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
Created by mustafa.urgupluoglu on 6/27/18.
 */
class NetworkModule{
    fun service(): ServiceInterface {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com")
                .client(clt)
                .build()

        return retrofit.create<ServiceInterface>(ServiceInterface::class.java)
    }
}
