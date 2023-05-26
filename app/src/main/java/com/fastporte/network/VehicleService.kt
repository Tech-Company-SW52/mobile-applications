package com.fastporte.network

import com.fastporte.models.CommentsSearch
import com.fastporte.models.Vehicle
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface VehicleService {
    @GET("vehicle/driver/{id}")
    fun getVehicleByDriverID(@Path("id") id: Int): Call<List<Vehicle>>
}