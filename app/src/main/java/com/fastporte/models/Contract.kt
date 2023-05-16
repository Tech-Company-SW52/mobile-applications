package com.fastporte.models

import java.sql.Time
import java.util.Date

class Contract(
    val id: Int,
    val subject: String,
    val from: String,
    val to: String,
    val date: Date,
    val timeDeparture: Time,
    val timeArrival: Time,
    val amount: String,
    val quantity: String,
    val visible: Boolean,
    val client: User,
    val driver: User,
    val status: Status
)