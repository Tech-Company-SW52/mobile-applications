package com.fastporte.network

import com.fastporte.models.Comment
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentsService {
    @GET("api/comments/driver/{id}")
    fun getCommentsByDriverID(@Path("id") id: Int): Call<List<Comment>>

    @POST("api/comments/add/{clientId}/{driverId}")
    fun postComment(
        @Path("clientId") clientId: Int,
        @Path("driverId") driverId: Int,
        @Body comment: Comment?
    ): Call<Comment>
}