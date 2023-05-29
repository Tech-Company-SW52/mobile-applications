package com.fastporte.models

import com.google.gson.annotations.SerializedName

class Vehicle(
    @SerializedName("id") var id: Int,
    @SerializedName("type") var type: String,
    @SerializedName("quantity") var quantity: Int,
    @SerializedName("photo") var photo: String,
    @SerializedName("driver") var driver: User
)