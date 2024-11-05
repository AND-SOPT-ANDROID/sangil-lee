package com.sopt.data.repository

import com.sopt.data.datasource.local.UserLocalDataSource
import com.sopt.data.datasource.remote.UserRemoteDataSource
import com.sopt.data.request.SignInRequest
import com.sopt.data.request.SignUpRequest
import com.sopt.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun signUp(username: String, password: String, hobby: String): Result<Unit> {
        return runCatching {
            userRemoteDataSource.signUp(SignUpRequest(username, password, hobby))
        }
    }

    override suspend fun signIn(username: String, password: String): Result<Unit> {
        return runCatching {
            userRemoteDataSource.signIn(SignInRequest(username, password))
        }
    }
}