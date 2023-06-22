package com.fastporte.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ContractPost (
    @SerializedName("subject") val subject: String,
    @SerializedName("from") val from: String,
    @SerializedName("to") val to: String,
    @SerializedName("contractDate") val contractDate: String,
    @SerializedName("timeDeparture") val timeDeparture: String,
    @SerializedName("timeArrival") val timeArrival: String,
    @SerializedName("amount") val amount: String,
    @SerializedName("quantity") val quantity: String,
    @SerializedName("description") val description: String,
    @SerializedName("visible") val visible: Boolean,
    @SerializedName("client") val client: User,
    @SerializedName("driver") val driver: User,
    @SerializedName("status") val status: Status,
    @SerializedName("notification") val notification: Notification
): Serializable