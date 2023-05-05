package com.fastport.Beans;

import com.google.gson.annotations.SerializedName
import java.util.*

class Drivers(
    @SerializedName("birthdate") var birthdate: String,
    @SerializedName("description") var description: String,
    @SerializedName("email") var email:String,
    @SerializedName("id") var id:Int,
    @SerializedName("name") var name: String,
    @SerializedName("lastname")var lastname: String,
    @SerializedName("username")var username: String,
    @SerializedName("phone")var phone:String,
    @SerializedName("region")var region: String,
    @SerializedName("password")var password: String,
    @SerializedName("photo")var photo:String
) {
}