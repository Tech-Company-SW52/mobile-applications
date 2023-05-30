package com.fastporte.network

import com.fastporte.models.Comment
import com.fastporte.models.Experience
import com.fastporte.models.User
import com.fastporte.models.Vehicle
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ProfileService {

    @GET("api/drivers/{id}")
    fun getDriverProfile(@Path("id") id: Int, @Query("format") format: String): Call<User>

    @GET("api/experience/{id}")
    fun getDriverExperience(@Path("id") id: Int, @Query("format") format: String): Call<List<Experience>>

    @GET("api/vehicle/driver/{id}")
    fun getDriverVehicle(@Path("id")    id: Int, @Query("format") format: String): Call<List<Vehicle>>

    @GET("api/comments/driver/{id}")
    fun getDriverComments(@Path("id") id: Int, @Query("format") format: String): Call<List<Comment>>

    @GET("api/clients/{id}")
    fun getClientProfile(@Path("id") id: Int, @Query("format") format: String): Call<User>

    @PUT("api/drivers/{id}")
    fun updateDriver(@Path("id") id: Int, @Body user: User?): Call<User>

    @PUT("api/clients/{id}")
    fun updateClient(@Path("id") id: Int, @Body user: User?): Call<User>

    @POST("api/experience/{id}")
    fun postExperienceDriver(@Path("id") id: Int, @Body experience: Experience?): Call<Experience>

    @POST("api/vehicle/{id}")
    fun postVehicleDriver(@Path("id") id: Int, @Body experience: Vehicle?): Call<Vehicle>
}