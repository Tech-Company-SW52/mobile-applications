package com.fastporte.network

import com.fastporte.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClientsService {
    @GET("clients")
    fun getClient(@Query("format") format: String): Call<List<User>>

    @GET("clients/{id}")
    fun getClient(@Path("id") id: Int, @Query("format") format: String ): Call<User>
}