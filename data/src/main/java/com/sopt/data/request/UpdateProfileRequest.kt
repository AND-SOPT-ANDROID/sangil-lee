package com.sopt.data.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileRequest(
    @SerialName("password") val password: String,
    @SerialName("hobby") val hobby: String
)