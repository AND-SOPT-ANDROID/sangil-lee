package com.sopt.data.datasource.local

import android.content.SharedPreferences
import com.sopt.data.UserEncryptedSharedPref
import com.sopt.data.UserSharedPref
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    @UserSharedPref private val userSharedPreferences: SharedPreferences,
    @UserEncryptedSharedPref private val userEncryptedSharedPref: SharedPreferences
) {

    fun saveToken(token: String) {
        userEncryptedSharedPref.edit()
            .putString(TOKEN, token)
            .apply()
    }

    companion object {
        private const val TOKEN = "token"
    }
}