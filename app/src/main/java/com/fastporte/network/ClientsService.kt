package com.fastporte.network

import com.fastporte.models.User
import retrofit2.Call
import retrofit2.http.*

interface ClientsService {
    @GET("clients")
    fun getClient(@Query("format") format: String): Call<List<User>>

    @GET("clients/{id}")
    fun getClient(@Path("id") id: Int, @Query("format") format: String ): Call<User>

    @PUT("clients/{id}")
    fun updateClient(@Path("id") id: Int, @Body user: User?): Call<User>

}