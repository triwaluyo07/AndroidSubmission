package com.triwaluyo07.androidsubmission2.api

import com.triwaluyo07.androidsubmission2.data.model.UserData
import com.triwaluyo07.androidsubmission2.data.model.UserResponse
import com.triwaluyo07.androidsubmission2.data.model.DetailUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token YOUR TOKEN")
    fun getSearchUsers(
            @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: YOUR TOKEN")
    fun getUserDetail(
            @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token YOUR TOKEN")
    fun getFollowers(
            @Path("username") username: String
    ): Call<ArrayList<UserData>>

    @GET("users/{username}/following")
    @Headers("Authorization: token YOUR TOKEN")
    fun getFollowing(
            @Path("username") username: String
    ): Call<ArrayList<UserData>>
}