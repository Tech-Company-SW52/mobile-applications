package com.fastporte.network

import com.fastporte.models.User
import retrofit2.Call
import retrofit2.http.*

interface DriversService {
    @GET("drivers")
    fun getDriver(@Query("format") format: String): Call<List<User>>

    @GET("drivers/{id}")
    fun getDriver(@Path("id") id: Int, @Query("format") format: String): Call<User>

    @PUT("drivers/{id}")
    fun updateDriver(@Path("id") id: Int, @Body user: User?): Call<User>
}