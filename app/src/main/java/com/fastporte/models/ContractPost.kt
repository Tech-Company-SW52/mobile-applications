package com.fastporte.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ContractPost (
    @SerializedName("id")
    val id: Int,
    @SerializedName("subject")
    val subject: String,
    @SerializedName("from")
    val from: String,
    @SerializedName("to")
    val to: String,
    @SerializedName("contractDate")
    val date: String,
    @SerializedName("timeDeparture")
    val timeDeparture: TimeDeparture,
    @SerializedName("timeArrival")
    val timeArrival: TimeArrival,
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
    val status: Status,
    @SerializedName("notification")
    val notification: Notification
): Serializable
data class TimeArrival(
    @SerializedName("hour") val hour: Int,
    @SerializedName("minute") val minute: Int,
    @SerializedName("nano") val nano: Int,
    @SerializedName("second") val second: Int
)

data class TimeDeparture(
    @SerializedName("hour") val hour: Int,
    @SerializedName("minute") val minute: Int,
    @SerializedName("nano") val nano: Int,
    @SerializedName("second") val second: Int
)