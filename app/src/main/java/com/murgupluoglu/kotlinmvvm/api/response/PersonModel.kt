package com.murgupluoglu.kotlinmvvm.api.response


/*
*  Created by Mustafa Ürgüplüoğlu on 27.05.2021.
*  Copyright © 2021 Mustafa Ürgüplüoğlu. All rights reserved.
*/

data class PersonModel(
    var name: PersonNameModel
)

data class PersonNameModel(
    val title: String,
    val first: String,
    val last: String,
)