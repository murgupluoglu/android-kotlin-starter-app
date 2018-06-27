package com.murgupluoglu.kotlinmvvm.di.koin

/**
Created by mustafa.urgupluoglu on 6/27/18.
 */
interface Repository {
    fun giveHello(): String
}

class MyRepository() : Repository {
    override fun giveHello() = "Hello Koin"
}