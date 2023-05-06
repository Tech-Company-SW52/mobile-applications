package com.fastport.Beans;

import com.google.gson.annotations.SerializedName
import java.util.Date


class Clients (
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

        ){

//
//    var emain: String
//    var id: Int
//    constructor(id:Int, birthdate: Date, description: String){
//        this.birthdate = birthdate
//        this.description = description
//        this.id = id
//    }



}