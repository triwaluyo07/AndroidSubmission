package com.triwaluyo07.androidsubmission2.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triwaluyo07.androidsubmission2.api.RetrofitClient
import com.triwaluyo07.androidsubmission2.data.model.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel: ViewModel() {

    val listFollowers = MutableLiveData<ArrayList<UserData>>()

    fun setListFollowers(username: String){
        RetrofitClient.apiInstance
                .getFollowers(username)
                .enqueue(object : Callback<ArrayList<UserData>>{
                    override fun onResponse(call: Call<ArrayList<UserData>>, response: Response<ArrayList<UserData>>) {
                        if(response.isSuccessful)
                        {
                            listFollowers.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<UserData>>, t: Throwable) {
                        Log.d("Failure", t.message.toString())
                    }

                })
    }

    fun getListFollowers(): LiveData<ArrayList<UserData>>{
        return listFollowers
    }
}