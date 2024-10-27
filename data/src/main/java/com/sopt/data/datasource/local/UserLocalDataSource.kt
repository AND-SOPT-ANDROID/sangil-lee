package com.sopt.data.datasource.local

import android.content.SharedPreferences
import com.sopt.data.UserInjectParam
import com.sopt.domain.exception.SignInError
import javax.inject.Inject
import javax.inject.Qualifier

class UserLocalDataSource @Inject constructor(
    @UserInjectParam private val userSharedPreferences: SharedPreferences
) {

    fun saveAccount(email: String, password: String) {
        userSharedPreferences.edit()
            .putString(KEY_EMAIL, email)
            .putString(KEY_PASSWORD, password)
            .apply()
    }

    fun trySignIn(email: String, password: String): Result<Unit> {
        val savedEmail = userSharedPreferences.getString(KEY_EMAIL, null)
        val savedPassword = userSharedPreferences.getString(KEY_PASSWORD, null)

        return if (email == savedEmail && password == savedPassword)
            Result.success(Unit)
        else if (email != savedEmail)
            Result.failure(SignInError.NotExistEmail())
        else
            Result.failure(SignInError.PasswordNotMatchingWithEmail())
    }

    companion object {
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
    }
}