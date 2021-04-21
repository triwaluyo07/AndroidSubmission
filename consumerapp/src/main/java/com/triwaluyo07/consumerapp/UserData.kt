package com.triwaluyo07.consumerapp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
        val login: String?,
        val id : Int?,
        @SerializedName("avatar_url")
        val avatarUrl : String?,
        val url : String?

): Parcelable