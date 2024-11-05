package com.sopt.domain.repository

import com.sopt.domain.model.User

interface UserRepository {
    suspend fun signUp(email: String, password: String, hobby: String): Result<Unit>
    fun trySignIn(email: String, password: String): Result<Unit>

    suspend fun fetchUser(): Result<User>
}