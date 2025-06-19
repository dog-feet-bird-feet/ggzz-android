package com.analysis.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)
