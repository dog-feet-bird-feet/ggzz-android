package com.analysis.presentation.feature.historydetail.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.analysis.domain.model.HistoryDetail
import com.analysis.presentation.model.ResultIndicator

@Stable
sealed interface HistoryDetailUiState {
    @Immutable
    data object Loading : HistoryDetailUiState

    @Immutable
    data class HistoryDetail(
        val title: String,
        val verificationImgUrl: String,
        val createdAt: String,
        val indicators: List<ResultIndicator>,
    ) : HistoryDetailUiState
}

fun HistoryDetail.toHistoryDetailUiState(): HistoryDetailUiState =
    HistoryDetailUiState.HistoryDetail(
        title = this.title,
        verificationImgUrl = this.verificationImgUrl,
        createdAt = this.createdAt,
        indicators = listOf(
            ResultIndicator.Similarity(similarity),
            ResultIndicator.Pressure(pressure),
            ResultIndicator.Inclination(inclination),
        ),
    )
