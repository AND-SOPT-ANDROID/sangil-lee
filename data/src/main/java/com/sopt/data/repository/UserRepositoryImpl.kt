package com.sopt.data.repository

import com.sopt.data.datasource.local.TokenLocalDataSource
import com.sopt.data.datasource.local.UserLocalDataSource
import com.sopt.data.datasource.remote.UserRemoteDataSource
import com.sopt.data.request.SignInRequest
import com.sopt.data.request.SignUpRequest
import com.sopt.domain.exception.SearchHobbyError
import com.sopt.domain.exception.SignInError
import com.sopt.domain.exception.SignUpError
import com.sopt.domain.exception.runCatchingByCode
import com.sopt.domain.exception.runSuspendCatching
import com.sopt.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
    private val tokenLocalDataSource: TokenLocalDataSource
) : UserRepository {

    override suspend fun signUp(username: String, password: String, hobby: String): Result<Unit> {
        return runCatchingByCode(0 to SignUpError.AlreadyExistUsername()) {
            userRemoteDataSource.signUp(SignUpRequest(username, password, hobby))
        }
    }

    override suspend fun signIn(username: String, password: String): Result<Unit> {
        return runCatchingByCode(
            2 to SignInError.NotExistUsername(),
            1 to SignInError.PasswordNotMatchingWithUsername()
        ) {
            val signInDto = userRemoteDataSource.signIn(SignInRequest(username, password))
            signInDto.token?.let { tokenLocalDataSource.saveToken(it) }
        }
    }

    override suspend fun fetchMyHobby(): Result<String> {
        return runSuspendCatching {
            val token = tokenLocalDataSource.getToken()
            userRemoteDataSource.fetchMyHobby(token).hobby ?: ""
        }
    }

    override suspend fun fetchUserHobby(no: Int): Result<String> {
        return runCatchingByCode(1 to SearchHobbyError.NotExistUserNo()) {
            val token = tokenLocalDataSource.getToken()
            userRemoteDataSource.fetchUserHobby(token, no).hobby ?: ""
        }
    }
}
