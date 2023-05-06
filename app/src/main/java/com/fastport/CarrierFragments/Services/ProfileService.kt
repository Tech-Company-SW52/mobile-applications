package com.fastport.CarrierFragments.Services

import com.fastport.CarrierClasses.Experience
import com.fastport.CarrierClasses.Information
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileService {
    @GET("api/drivers/1")
    fun getProfile(@Query("format") format: String) : Call<Information>

    @GET("api/experience/1")
    fun getExperience(@Query("format") format: String) : Call<ArrayList<Experience>>
}