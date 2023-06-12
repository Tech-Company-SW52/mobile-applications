package com.fastporte.network

import com.fastporte.models.ClientNotification
import com.fastporte.models.DriverNotification
import com.fastporte.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NotificationService {
    @GET("contracts/notifications-driver/{id}")
    fun getDriverNotifiacations(@Path("id") id: Int, @Query("format") format: String): Call<List<DriverNotification>>

    @GET("contracts/notifications-client/{id}")
    fun getClientNotifiacations(@Path("id") id: Int, @Query("format") format: String): Call<List<ClientNotification>>

    @GET("api/contracts/{id}")
    fun getContract(@Path("id") id: Int, @Query("format") format: String): Call<ClientNotification>
}