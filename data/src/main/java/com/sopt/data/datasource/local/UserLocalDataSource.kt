package com.sopt.data.datasource.local

import android.content.SharedPreferences
import com.sopt.data.UserSharedPref
import com.sopt.domain.model.Account
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    @UserSharedPref private val userSharedPreferences: SharedPreferences,
) {

    fun saveAccount(account: Account) {
        userSharedPreferences.edit()
            .putString(KEY_USERNAME, account.username)
            .putString(KEY_PASSWORD, account.password)
            .apply()
    }

    fun getAccount(): Account? {
        val username = userSharedPreferences.getString(KEY_USERNAME, null)
        val password = userSharedPreferences.getString(KEY_PASSWORD, null)
        return if (username != null && password != null) {
            Account(username, password)
        } else {
            null
        }
    }

    companion object {
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }
}