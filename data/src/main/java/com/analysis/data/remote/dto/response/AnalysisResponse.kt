package com.analysis.data.remote.dto.response

import com.analysis.domain.model.AnalysisResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnalysisResponse(
    @SerialName("similarity") val similarity: Float,
    @SerialName("pressure") val pressure: Float,
    @SerialName("inclination") val inclination: Float,
)

fun AnalysisResponse.toAnalysisResult() = AnalysisResult(
    similarity = similarity,
    pressure = pressure,
    inclination = inclination,
)
