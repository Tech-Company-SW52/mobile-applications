package com.fastporte.network

import com.fastporte.controller.fragments.ClientFragments.ClientRequestServiceFragment
import com.fastporte.models.JsonResponse
import com.fastporte.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GeoCodeService {
    @GET("geocode/v1/json")
    fun getFormattedResults(
        @Query("q") query: String,
        @Query("key") apiKey: String,
        @Query("language") language: String,
        @Query("pretty") pretty: Int
    ): Call<JsonResponse>
}