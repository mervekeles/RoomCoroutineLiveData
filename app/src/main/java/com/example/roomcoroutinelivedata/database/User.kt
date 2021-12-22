package com.example.roomcoroutinelivedata.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @SerializedName("first_name")@ColumnInfo(name = "first_name") val firstName: String?,
    @SerializedName("last_name")@ColumnInfo(name = "last_name") val lastName: String?, val email: String?, val avatar: String?)