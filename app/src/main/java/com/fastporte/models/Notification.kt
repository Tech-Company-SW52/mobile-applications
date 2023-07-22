package com.fastporte.models

import com.google.gson.annotations.SerializedName

class Notification(
    @SerializedName("id")
    val id: Int,
    @SerializedName("readStatus")
    val readStatus: Boolean,
)