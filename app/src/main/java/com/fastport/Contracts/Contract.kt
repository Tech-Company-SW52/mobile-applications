package com.fastport.Contracts

import java.util.Date

class Contract (
    val id: Int,
    val subject: String,
    val from: String,
    val to: String,
    val date: String,
    val timeDeparture: String,
    val timeArrival: String,
//    val date: Date,
//    val timeDeparture: Date,
//    val timeArrival: Date,
    val amount: Int,
    val quantity: Int,
    val description: String,
    val visible: Boolean
    )