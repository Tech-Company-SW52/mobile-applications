package com.fastporte.network

import com.fastporte.models.Contract
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ContractsService {
    @GET("api/contracts/offer/driver/{id}")
    fun getOfferContractsByDriverId(
        @Path("id") id: Int,
        @Query("format") format: String
    ): Call<List<Contract>>

    @GET("api/contracts/pending/{user}/{id}")
    fun getPendingContractsByUserAndId(
        @Path("id") id: Int,
        @Path("user") user: String,
        @Query("format") format: String
    ): Call<List<Contract>>

    @GET("api/contracts/history/{user}/{id}")
    fun getHistoryContractsByUserAndId(
        @Path("id") id: Int,
        @Path("user") user: String,
        @Query("format") format: String
    ): Call<List<Contract>>

    // change read status to true or false
    @PUT("api/contracts/{idContract}/change-notification-status")
    fun changeNotificationStatus(
        @Path("idContract") idContract: Int,
        @Query("format") format: String
    ): Call<Contract>

    // visible to false
    @PUT("api/contracts/{idContract}/change-visible")
    fun changeVisible(
        @Path("idContract") idContract: Int,
        @Query("format") format: String
    ): Call<Contract>

    // update contract status
    @PUT("api/contracts/{idContract}/update-status/{idContractStatus}")
    fun updateContractStatus(
        @Path("idContract") idContract: Int,
        @Path("idContractStatus") idContractStatus: Int,
        @Query("format") format: String
    ): Call<Contract>
}