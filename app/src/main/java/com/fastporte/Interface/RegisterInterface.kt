package com.fastporte.Interface

import com.fastporte.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterInterface {
    @POST("clients")
    fun registerClient(@Body clientData: User): Call<User>

    @POST("drivers")
    fun registerDriver(@Body driverData: User): Call<User>
}