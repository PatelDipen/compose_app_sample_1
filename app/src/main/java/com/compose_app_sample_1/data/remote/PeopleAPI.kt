package com.compose_app_sample_1.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleAPI {

    @GET("people/")
    suspend fun getPeoples(@Query("page") page: Int): PeopleDTO

    @GET("people/{id}")
    suspend fun getPeople(@Path("id") peopleId: Int): PeopleDetailDTO
}

