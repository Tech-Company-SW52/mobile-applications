package com.fastporte.network

import com.fastporte.models.CommentsSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentsService {
    @GET("comments/driver/{id}")
    fun getCommentsByDriverID(@Path("id") id: Int): Call<List<CommentsSearch>>
}