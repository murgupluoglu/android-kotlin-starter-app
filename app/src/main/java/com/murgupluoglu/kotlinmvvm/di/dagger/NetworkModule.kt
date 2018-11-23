package com.murgupluoglu.kotlinmvvm.di.dagger

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.murgupluoglu.kotlinmvvm.service.ServiceInterface
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideServiceApi(): ServiceInterface {
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
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com")
                .client(clt)
                .build()

        return retrofit.create<ServiceInterface>(ServiceInterface::class.java)
    }
}
