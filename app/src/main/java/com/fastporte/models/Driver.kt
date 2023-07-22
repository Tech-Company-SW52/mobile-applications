package com.fastporte.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.io.Serializable

@Entity(tableName = "popular_drivers")
class Driver (
    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0,

    @ColumnInfo
    @SerializedName("name")
    var name: String,

    @ColumnInfo
    @SerializedName("lastname")
    var lastname: String,

    @ColumnInfo
    @SerializedName("username")
    var username: String,

    @ColumnInfo
    @SerializedName("photo")
    var photo: String,

    @ColumnInfo
    @SerializedName("email")
    var email: String,

    @ColumnInfo
    @SerializedName("phone")
    var phone: String,

    @ColumnInfo
    @SerializedName("region")
    var region: String,

    @ColumnInfo
    @SerializedName("birthday")
    var birthday: String?,

    @ColumnInfo
    @SerializedName("password")
    var password: String,

    @ColumnInfo
    @SerializedName("description")
    var description: String,
) : Serializable