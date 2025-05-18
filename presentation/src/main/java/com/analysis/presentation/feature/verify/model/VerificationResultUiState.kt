package com.analysis.presentation.feature.verify.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.analysis.domain.model.AnalysisResult
import com.analysis.presentation.model.ResultIndicator

@Stable
sealed interface VerificationResultUiState {
    @Immutable
    data object Loading : VerificationResultUiState

    @Immutable
    data class VerificationResult(
        val indicators: List<ResultIndicator>,
    ) : VerificationResultUiState
}

fun AnalysisResult.toVerificationResultUiState(): VerificationResultUiState =
    VerificationResultUiState.VerificationResult(
        indicators = listOf(
            ResultIndicator.Similarity(similarity),
            ResultIndicator.Pressure(pressure),
            ResultIndicator.Inclination(inclination),
        ),
    )
