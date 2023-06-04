package com.fastporte.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.sql.Time
import java.time.LocalTime

class Experience(

    @SerializedName("id")
    var id: Int,
    @SerializedName("job")
    var job: String,
    @SerializedName("years")
    var years: Int
) : Serializable

