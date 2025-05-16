package com.analysis.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnalysisRequest(
    @SerialName("verificationImageUrl") val verificationImageUrl: String,
    @SerialName("comparisonImageUrls") val comparisonImageUrls: List<String>,
)
