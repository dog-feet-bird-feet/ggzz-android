package com.analysis.presentation.feature.historydetail.model

import com.analysis.domain.model.AnalysisResult
import java.time.LocalDateTime

data class VerificationResultUiModel(
    val title: String,
    val imageUrl: String,
    val createdAt: String,
    val indicators: List<VerificationIndicator>,
)

fun AnalysisResult.toVerificationResultUiModel(): VerificationResultUiModel {
    return VerificationResultUiModel(
        title = title,
        imageUrl = verificationImgUrl,
        createdAt = createdAt,
        indicators = listOf(
            VerificationIndicator.Similarity(similarity),
            VerificationIndicator.Pressure(pressure),
            VerificationIndicator.Inclination(inclination),
        ),
    )
}
