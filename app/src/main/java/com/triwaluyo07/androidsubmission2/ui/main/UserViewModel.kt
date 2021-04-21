package com.triwaluyo07.androidsubmission2.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triwaluyo07.androidsubmission2.api.RetrofitClient
import com.triwaluyo07.androidsubmission2.data.model.UserData
import com.triwaluyo07.androidsubmission2.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<UserData>>()

    fun setSearchUsers(query: String){
        RetrofitClient.apiInstance
                .getSearchUsers(query)
                .enqueue(object : Callback<UserResponse>{
                    override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                        if(response.isSuccessful){
                            listUsers.postValue(response.body()?.items)
                        }
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        Log.d("Failure", t.message.toString())
                    }

                })

    }

    fun getSearchUsers(): LiveData<ArrayList<UserData>>{
        return listUsers
    }
}