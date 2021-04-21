package com.triwaluyo07.consumerapp

import android.database.Cursor

object MappingHelper {
    fun mapCursortoArrayList(cursor: Cursor?): ArrayList<UserData>{
        val list = ArrayList<UserData>()
        if (cursor != null){
            while (cursor.moveToNext()){
                val login = cursor.getString(cursor.getColumnIndexOrThrow(
                    DbContract.FavUserColumns.LOGIN))
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(
                    DbContract.FavUserColumns.ID))
                val avatarUrl = cursor.getString(cursor.getColumnIndexOrThrow(
                    DbContract.FavUserColumns.AVATAR_URL))
                val url = cursor.getString(cursor.getColumnIndexOrThrow(
                    DbContract.FavUserColumns.URL))
                list.add(
                    UserData(
                        login,
                        id,
                        avatarUrl,
                        url
                    )
                )
            }
        }
        return list
    }
}