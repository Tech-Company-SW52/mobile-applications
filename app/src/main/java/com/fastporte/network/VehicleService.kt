package com.fastporte.network

import com.fastporte.models.Vehicle
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VehicleService {
    @GET("vehicle/driver/{id}")
    fun getVehicleByDriverID(@Path("id") id: Int): Call<List<Vehicle>>

    @GET("vehicle/find/{type}/{quantity}")
    fun getVehicleFindTypeQuantity(
        @Path("type") type: String,
        @Path("quantity") quantity: String,
        @Query("format") format: String
    ): Call<List<Vehicle>>
}