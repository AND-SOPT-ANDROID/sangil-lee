package com.sopt.data.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileRequest(
    val password: String,
    val hobby: String
)
