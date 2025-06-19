package com.analysis.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonalityAnalyzeRequest(
    @SerialName("imageUrl") val imageUrl: String,
)
