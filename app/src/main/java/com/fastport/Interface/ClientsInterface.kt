package com.fastport.Interface

import com.fastport.Beans.Clients
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClientsInterface {
    @GET("clients")
    fun getClient(@Query("format") format: String): Call<List<Clients>>

    @GET("clients/{id}")
    fun getClient(@Query("format") format: String, @Path("id")id:Int):Call<Clients>
}