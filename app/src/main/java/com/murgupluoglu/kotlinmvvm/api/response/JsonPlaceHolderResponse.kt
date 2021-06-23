package com.murgupluoglu.kotlinmvvm.api.response


/*
*  Created by Mustafa Ürgüplüoğlu on 27.05.2021.
*  Copyright © 2021 Mustafa Ürgüplüoğlu. All rights reserved.
*/

data class JsonPlaceHolderResponse(
    var userId: Int = 0,
    var id: Int = 0,
    var title: String = "",
    var body: String = ""
)