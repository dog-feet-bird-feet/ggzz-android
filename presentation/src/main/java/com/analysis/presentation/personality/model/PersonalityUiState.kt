package com.analysis.presentation.personality.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.analysis.domain.model.Personality

@Stable
internal sealed interface PersonalityUiState {
    @Immutable
    data object ImageUploadState : PersonalityUiState

    @Stable
    sealed interface Analyzing : PersonalityUiState {
        @Immutable
        data object Loading : Analyzing

        @Immutable
        data class Success(
            val personality: Personality,
        ) : Analyzing
    }
}

internal fun Personality.toPersonalityUiState() = PersonalityUiState.Analyzing.Success(this)
