package com.sopt.data.datasource.local

import android.content.SharedPreferences
import com.sopt.data.TokenEncryptedSharedPref
import javax.inject.Inject

class TokenLocalDataSource @Inject constructor(
    @TokenEncryptedSharedPref private val tokenEncryptedSharedPref: SharedPreferences
) {

    fun saveToken(token: String) {
        tokenEncryptedSharedPref.edit()
            .putString(TOKEN, token)
            .apply()
    }

    fun getToken(): String {
        return tokenEncryptedSharedPref.getString(TOKEN, "").orEmpty()
    }

    companion object {
        private const val TOKEN = "token"
    }
}