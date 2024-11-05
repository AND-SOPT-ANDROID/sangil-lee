package com.sopt.data.datasource.remote

import com.sopt.data.api.remote.UserApi
import com.sopt.data.request.SignUpRequest
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userApi: UserApi
) {

    suspend fun signUp(signUpRequest: SignUpRequest) {
        return userApi.signUp(signUpRequest)
    }
}