package com.sopt.data.api.remote

import com.sopt.data.request.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("user")
    suspend fun signUp(@Body signUpRequest: SignUpRequest)
}