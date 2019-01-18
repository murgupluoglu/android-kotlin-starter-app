package com.murgupluoglu.kotlinmvvm.model

/**
 * Created by Mustafa Urgupluoglu on 18.01.2019.
 */
data class PeopleResponse(
    val info: Info,
    val results: List<User> = arrayListOf()
)

data class Info(
    val page: Int,
    val results: Int,
    val seed: String,
    val version: String
)

data class User(
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    val id: Id,
    val location: Location,
    val login: Login,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
    val registered: Registered
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)

data class Login(
    val md5: String,
    val password: String,
    val salt: String,
    val sha1: String,
    val sha256: String,
    val username: String,
    val uuid: String
)

data class Dob(
    val age: Int,
    val date: String
)

data class Location(
    val city: String,
    val coordinates: Coordinates,
    val postcode: String,
    val state: String,
    val street: String,
    val timezone: Timezone
)

data class Timezone(
    val description: String,
    val offset: String
)

data class Coordinates(
    val latitude: String,
    val longitude: String
)

data class Name(
    val first: String,
    val last: String,
    val title: String
)

data class Id(
    val name: String,
    val value: String
)

data class Registered(
    val age: Int,
    val date: String
)