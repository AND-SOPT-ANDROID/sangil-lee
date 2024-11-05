package com.sopt.data.datasource.local

import android.content.SharedPreferences
import com.sopt.data.UserSharedPref
import com.sopt.domain.exception.SignInError
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    @UserSharedPref private val userSharedPreferences: SharedPreferences
) {

    fun trySignIn(email: String, password: String): Result<Unit> {
        val savedEmail = userSharedPreferences.getString(KEY_EMAIL, null)
        val savedPassword = userSharedPreferences.getString(KEY_PASSWORD, null)

        return when {
            email == savedEmail && password == savedPassword ->
                Result.success(Unit)

            email != savedEmail ->
                Result.failure(SignInError.NotExistEmail())

            else ->
                Result.failure(SignInError.PasswordNotMatchingWithEmail())
        }
    }

    companion object {
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
    }
}