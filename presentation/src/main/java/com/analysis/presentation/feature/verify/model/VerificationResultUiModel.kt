package com.analysis.presentation.feature.verify.model

import androidx.compose.runtime.Stable

@Stable
sealed interface VerificationResultUiModel {
}

/*@Stable
sealed interface HistoryDetailUiState {
    @Immutable
    data object Loading : HistoryDetailUiState

    @Immutable
    data class HistoryDetail(
        val title: String,
        val verificationImgUrl: String,
        val createdAt: String,
        val indicators: List<VerificationIndicator>,
    ) : HistoryDetailUiState
}

fun HistoryDetail.toHistoryDetailUiState(): HistoryDetailUiState =
    HistoryDetailUiState.HistoryDetail(
        title = this.title,
        verificationImgUrl = this.verificationImgUrl,
        createdAt = this.createdAt,
        indicators = listOf(
            VerificationIndicator.Similarity(similarity),
            VerificationIndicator.Pressure(pressure),
            VerificationIndicator.Inclination(inclination),
        ),
    )*/

