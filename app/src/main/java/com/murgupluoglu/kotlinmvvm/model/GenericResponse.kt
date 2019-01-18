package com.murgupluoglu.kotlinmvvm.model

/**
 * Created by Mustafa Urgupluoglu on 18.01.2019.
 */

data class GenericResponse(
        var hasError : Boolean = false,
        var errorCode : Int = -1,
        var errorMessage : String = "",
        var responseObject : Any = -1
)