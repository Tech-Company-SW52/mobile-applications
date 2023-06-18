package com.fastporte.models

import android.provider.ContactsContract.CommonDataKinds.Email
import com.google.gson.annotations.SerializedName

class CardClient (
//    @SerializedName("id")
//    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("holderName")
    val holderName: String,
    @SerializedName("number")
    val cardNumber: Long,
    @SerializedName("expirationDate")
    val expirationDate: String,
    @SerializedName("cvv")
    val cvv: Int,
    @SerializedName("issuer")
    val issuer: String,
    @SerializedName("nickname")
    val nickname: String
        )