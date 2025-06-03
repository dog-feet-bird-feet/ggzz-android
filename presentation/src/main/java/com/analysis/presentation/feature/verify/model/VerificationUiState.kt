package com.analysis.presentation.feature.verify.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.analysis.domain.model.AnalysisResult
import com.analysis.presentation.model.ResultIndicator

@Stable
internal sealed interface VerificationUiState {
    @Immutable
    data object ComparisonUploadState : VerificationUiState

    @Immutable
    data object VerificationUploadState : VerificationUiState

    @Stable
    sealed interface Verification : VerificationUiState {
        @Immutable
        data object Loading : Verification

        @Immutable
        data class Success(
            val indicators: List<ResultIndicator>,
        ) : Verification
    }
}

internal fun AnalysisResult.toVerificationResultUiState(): VerificationUiState =
    VerificationUiState.Verification.Success(
        indicators = listOf(
            ResultIndicator.Similarity(similarity),
            ResultIndicator.Pressure(pressure),
            ResultIndicator.Inclination(inclination),
        ),
    )
