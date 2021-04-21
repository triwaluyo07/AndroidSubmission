package com.triwaluyo07.androidsubmission2.data.local


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "favorite_user")

class FavoriteUser(
    val login: String?,
    @PrimaryKey
    val id: Int,
    val avatar_url : String?,
    val url: String?

) : Serializable