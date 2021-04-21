package com.triwaluyo07.consumerapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private var list = MutableLiveData<ArrayList<UserData>>()

    fun setFavUser(context: Context) {
        val cursor = context.contentResolver.query(
            DbContract.FavUserColumns.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        val listConverted = MappingHelper.mapCursortoArrayList(cursor)
        list.postValue(listConverted)
    }


    fun getFavoriteUser(): LiveData<ArrayList<UserData>> {
        return list
    }
}