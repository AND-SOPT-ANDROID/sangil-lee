package com.sopt.data.datasource.local

import android.content.SharedPreferences
import com.sopt.domain.exception.SignInError
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val userSharedPreferences: SharedPreferences
) {

    fun saveAccount(email: String, password: String) {
        userSharedPreferences.edit()
            .putString(KEY_EMAIL, email)
            .putString(KEY_PASSWORD, password)
            .apply()
    }

    fun trySignIn(email: String, password: String): Result<Unit> {
        return runCatching {
            if (email == userSharedPreferences.getString(KEY_EMAIL, null) &&
                password == userSharedPreferences.getString(KEY_PASSWORD, null)
            )
                Result.success(Unit)
            else if (email != userSharedPreferences.getString(KEY_EMAIL, null))
                Result.failure(SignInError.NotExistEmail())
            else
                Result.failure(SignInError.PasswordNotMatchingWithEmail())
        }
    }

    companion object {
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
    }
}