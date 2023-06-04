package com.fastporte.network

import com.fastporte.models.CommentsSearch
import com.fastporte.models.Experience
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ExperienceService {
    @GET("experience/{id}")
    fun getExperienceByDriverID(@Path("id") id: Int): Call<List<Experience>>
}