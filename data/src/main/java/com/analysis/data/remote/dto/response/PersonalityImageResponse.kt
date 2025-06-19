package com.analysis.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonalityImageResponse(
    @SerialName("personalityUrl") val personalityUrl: String,
)
