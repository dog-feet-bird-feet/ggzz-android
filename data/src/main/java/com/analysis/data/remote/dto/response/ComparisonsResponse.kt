package com.analysis.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComparisonsResponse(
    @SerialName("comparisonUrls") val comparisonUrls: List<String>,
)
