package com.fastport.CarrierFragments.Services

import com.fastport.CarrierClasses.Information
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileService {
    @GET("api/drivers/1")
    fun getProfile(@Query("format") format: String) : Call<Information>
}