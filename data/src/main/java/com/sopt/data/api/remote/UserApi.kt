package com.sopt.data.api.remote

import com.sopt.data.dto.user.HobbyDto
import com.sopt.data.dto.user.SignInDto
import com.sopt.data.dto.user.SignUpDto
import com.sopt.data.request.SignInRequest
import com.sopt.data.request.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @POST("user")
    suspend fun signUp(@Body signUpRequest: SignUpRequest): SignUpDto

    @POST("login")
    suspend fun signIn(@Body signInRequest: SignInRequest): SignInDto

    @GET("user/my-hobby")
    suspend fun fetchMyHobby(@Header("token") token: String): HobbyDto

    @GET("user/{no}/hobby")
    suspend fun fetchUserHobby(@Header("token") token: String, @Path("no") no: Int): HobbyDto
}