package com.fastporte.models

class ClientNotification (
    val id: Int,
    val subject: String,
    val from: String,
    val to: String,
    val contractDate: String,
    val timeDeparture: String,
    val timeArrival: String,
    val amount: String,
    val quantity: String,
    val description: String,
    val visible: Boolean,
    val client: User,
    val driver: User,
    val status: Status,
    val notification: Notification
)