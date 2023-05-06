package com.fastport.Interface

import com.fastport.Beans.Clients
import com.fastport.Beans.Drivers
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterInterface {
    @POST("clients")
    fun registerClient(@Body clientData: Clients): Call<Clients>

    @POST("drivers")
    fun registerDriver(@Body driverData: Drivers): Call<Drivers>
}