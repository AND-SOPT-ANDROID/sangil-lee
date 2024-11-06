package com.sopt.data.datasource.remote

import com.sopt.data.api.remote.UserApi
import com.sopt.data.dto.user.HobbyDto
import com.sopt.data.dto.user.SignInDto
import com.sopt.data.dto.user.SignUpDto
import com.sopt.data.request.SignInRequest
import com.sopt.data.request.SignUpRequest
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userApi: UserApi
) {

    suspend fun signUp(signUpRequest: SignUpRequest): SignUpDto {
        return userApi.signUp(signUpRequest)
    }

    suspend fun signIn(signInRequest: SignInRequest): SignInDto {
        return userApi.signIn(signInRequest)
    }

    suspend fun getMyHobby(token: String): HobbyDto {
        return userApi.getMyHobby(token)
    }
}