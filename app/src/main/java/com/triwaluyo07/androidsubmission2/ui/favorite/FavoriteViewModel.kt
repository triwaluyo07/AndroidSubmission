package com.triwaluyo07.androidsubmission2.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.triwaluyo07.androidsubmission2.data.local.FavoriteUser
import com.triwaluyo07.androidsubmission2.data.local.FavoriteUserDao
import com.triwaluyo07.androidsubmission2.data.local.UserDatabase

class FavoriteViewModel(application: Application)
    : AndroidViewModel(application){

    private var userDB: UserDatabase? = UserDatabase.getDatabase(application)
    private var userDao: FavoriteUserDao? = userDB?.favoriteUserData()


    fun getFavoriteUser(): LiveData<List<FavoriteUser>>? {
        return userDao?.getFavoriteUser()
    }
}