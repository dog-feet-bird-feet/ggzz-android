package com.analysis.presentation.personality.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.analysis.domain.model.Personality

@Stable
sealed interface PersonalityUiState {
    @Immutable
    data object ImageUploadState : PersonalityUiState

    @Immutable
    data object Loading : PersonalityUiState

    @Immutable
    data class ResultUiState(
        val personality: Personality,
    ) : PersonalityUiState
}

fun Personality.toPersonalityUiState() = PersonalityUiState.ResultUiState(
    personality = this
)
