package com.sopt.domain.repository

import com.sopt.domain.model.Account
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun signUp(username: String, password: String, hobby: String): Result<Unit>
    suspend fun signIn(username: String, password: String): Result<Unit>
    fun fetchMyHobby(): Flow<String>
    suspend fun fetchUserHobby(no: Int): Result<String>
    suspend fun updateProfile(password: String, hobby: String): Result<Unit>
    suspend fun saveAccount(account: Account): Result<Unit>
    suspend fun getAccount(): Account?
}