package com.sopt.domain.repository

interface UserRepository {
    fun saveAccount(email: String, password: String)
    fun trySignIn(email: String, password: String): Result<Unit>
}