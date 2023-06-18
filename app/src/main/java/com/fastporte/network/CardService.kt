package com.fastporte.network

import com.fastporte.models.CardClient
import com.fastporte.models.Vehicle
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CardService {

    @POST("api/cardsClient/{idClient}/add")
    fun postCardClient(@Path("idClient") id: Int,
                       @Body cardClient: CardClient?):
            Call<CardClient>

    @PUT("api/contracts/{idContract}/update-status/{idContractStatus}")
    fun updateContractStatus(@Path("idContract") idContract: Int,
                             @Path("idContractStatus") idContractStatus: Int):
            Call<CardClient>

    @POST("api/cardsDriver/{idDriver}/add")
    fun postCardDriver(@Path("idDriver") id: Int,
                       @Body cardClient: CardClient?):
            Call<CardClient>
}