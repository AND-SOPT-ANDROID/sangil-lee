package com.sopt.data.repository

import com.sopt.data.datasource.local.UserLocalDataSource
import com.sopt.data.datasource.remote.UserRemoteDataSource
import com.sopt.data.request.SignUpRequest
import com.sopt.domain.model.User
import com.sopt.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun signUp(email: String, password: String, hobby: String): Result<Unit> {
        return runCatching {
            userRemoteDataSource.signUp(SignUpRequest(email, password, hobby))
        }
    }

    override fun trySignIn(email: String, password: String): Result<Unit> {
        return userLocalDataSource.trySignIn(email, password)
    }

    override suspend fun fetchUser(): Result<User> {
        TODO("Not yet implemented")
    }
}