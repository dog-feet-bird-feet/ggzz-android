package com.analysis.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnalysisResponse(
    @SerialName("similarity") val similarity: Float,
    @SerialName("pressure") val pressure: Float,
    @SerialName("inclination") val inclination: Float,
    @SerialName("verificationImgUrl") val verificationImgUrl: String,
    @SerialName("createdAt") val createdAt: String,
)
