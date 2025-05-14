package com.analysis.presentation.feature.historydetail.model

import com.analysis.domain.model.VerificationResult
import java.time.LocalDateTime

data class VerificationResultUiModel(
    val title: String,
    val imageUrl: String,
    val createdAt: LocalDateTime,
    val indicators: List<VerificationIndicator>,
)

fun VerificationResult.toVerificationResultUiModel(): VerificationResultUiModel {
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
