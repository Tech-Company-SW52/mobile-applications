package com.fastporte.models

import com.google.gson.annotations.SerializedName
import java.util.Date

class Contract(
    @SerializedName("id")
    val id: Int,
    @SerializedName("subject")
    val subject: String,
    @SerializedName("from")
    val from: String,
    @SerializedName("to")
    val to: String,
    @SerializedName("contractDate")
    val date: Date,
    @SerializedName("timeDeparture")
    val timeDeparture: String,
    @SerializedName("timeArrival")
    val timeArrival: String,
    @SerializedName("amount")
    val amount: String,
    @SerializedName("quantity")
    val quantity: String,
    @SerializedName("visible")
    val visible: Boolean,
    @SerializedName("client")
    val client: User,
    @SerializedName("driver")
    val driver: User,
    @SerializedName("status")
    val status: Status
)