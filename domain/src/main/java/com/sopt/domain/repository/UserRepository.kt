package com.sopt.domain.repository

interface UserRepository {
    suspend fun signUp(username: String, password: String, hobby: String): Result<Unit>
    suspend fun signIn(username: String, password: String): Result<Unit>
    suspend fun fetchMyHobby(): Result<String>
}