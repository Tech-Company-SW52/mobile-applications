package com.fastporte.network

import com.fastporte.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DriversService {
    @GET("drivers")
    fun getDriver(@Query("format") format: String): Call<List<User>>

    @GET("drivers/{id}")
    fun getDriver(@Path("id") id: Int, @Query("format") format: String): Call<User>
}