package com.fastporte.network

import com.fastporte.models.Comment
import com.fastporte.models.Experience
import com.fastporte.models.Information
import com.fastporte.models.Vehicle
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProfileService {

    @GET("api/drivers/{id}")
    fun getDriverProfile(@Path("id") id: Int, @Query("format") format: String): Call<Information>

    @GET("api/experience/{id}")
    fun getDriverExperience(@Path("id") id: Int, @Query("format") format: String): Call<List<Experience>>

    @GET("api/vehicle/driver/{id}")
    fun getDriverVehicle(@Path("id")    id: Int, @Query("format") format: String): Call<List<Vehicle>>

    @GET("api/comments/driver/{id}")
    fun getDriverComments(@Path("id") id: Int, @Query("format") format: String): Call<List<Comment>>

    @GET("api/clients/{id}")
    fun getClientProfile(@Path("id") id: Int, @Query("format") format: String): Call<Information>

}