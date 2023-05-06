package com.fastport.Interface

import com.fastport.Beans.Drivers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DriversInterface {
    @GET("drivers")
    fun getDriver(@Query("format") format: String): Call<List<Drivers>>

    @GET("drivers/{id}")
    fun getDriver(@Query("format") format: String, @Path("id")id:Int): Call<Drivers>
}