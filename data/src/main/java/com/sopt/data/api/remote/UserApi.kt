package com.sopt.data.api.remote

import com.sopt.data.dto.user.SignInDto
import com.sopt.data.dto.user.SignUpDto
import com.sopt.data.request.SignInRequest
import com.sopt.data.request.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("user")
    suspend fun signUp(@Body signUpRequest: SignUpRequest): SignUpDto

    @POST("login")
    suspend fun signIn(@Body signInRequest: SignInRequest): SignInDto
}