package com.fastporte.network

import com.fastporte.models.Driver
import com.fastporte.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeService {
    @GET("api/drivers")
    fun getDriver(@Query("format") format: String): Call<List<Driver>>

}