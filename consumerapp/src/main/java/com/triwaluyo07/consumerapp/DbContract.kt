package com.triwaluyo07.consumerapp

import android.net.Uri
import android.provider.BaseColumns

object DbContract {
    const val AUTHORITY = "com.triwaluyo07.androidsubmission2"
    const val SCHEME = "content"

    internal class FavUserColumns: BaseColumns{
        companion object{
            const val TABLE_NAME = "favorite_user"
            const val ID = "id"
            const val LOGIN = "login"
            const val AVATAR_URL = "avatar_url"
            const val URL = "url"

            val CONTENT_URI = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}