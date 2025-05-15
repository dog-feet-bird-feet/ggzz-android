package com.analysis.data.remote.dto.response

import com.analysis.domain.model.AnalysisResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HistoryDetailResponse(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("similarity") val similarity: Float,
    @SerialName("pressure") val pressure: Float,
    @SerialName("inclination") val inclination: Float,
    @SerialName("verificationImgUrl") val verificationImgUrl: String,
    @SerialName("createdAt") val createdAt: String,
)

fun HistoryDetailResponse.toAnalysisResult() =
    AnalysisResult(
        id = this.id,
        title = this.title,
        similarity = this.similarity,
        pressure = this.pressure,
        inclination = this.inclination,
        verificationImgUrl = this.verificationImgUrl,
        createdAt = this.createdAt,
    )
