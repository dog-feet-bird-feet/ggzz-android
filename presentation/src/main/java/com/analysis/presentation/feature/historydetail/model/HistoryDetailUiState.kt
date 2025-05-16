package com.analysis.presentation.feature.historydetail.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.analysis.domain.model.HistoryDetail

@Stable
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
    )
