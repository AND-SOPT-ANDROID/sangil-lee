package com.sopt.data.dto.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HobbyDto(
    @SerialName("hobby") val hobby: String?
)