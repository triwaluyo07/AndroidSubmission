package com.triwaluyo07.androidsubmission2.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailUserResponse(
        val login: String?,
        val id : Int?,
        @SerializedName("avatar_url")
        val avatarUrl : String?,
        @SerializedName("followers_url")
        val followersUrl : String?,
        @SerializedName("following_url")
        val followingUrl : String?,
        val name: String?,
        val following: String?,
        val followers: String?,
        @SerializedName("public_repos")
        val publicRepos: String?,
        val company: String?,
        val location: String?
) : Parcelable