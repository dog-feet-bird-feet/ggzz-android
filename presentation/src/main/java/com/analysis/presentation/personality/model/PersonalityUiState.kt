package com.analysis.presentation.personality.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.analysis.domain.model.Personality
import com.analysis.domain.model.TraitDetail

@Stable
sealed interface PersonalityUiState {
    @Immutable
    data object ImageUploadState : PersonalityUiState

    @Immutable
    data object Loading : PersonalityUiState

    @Immutable
    data class ResultUiState(
        val size: TraitDetail,
        val pressure: TraitDetail,
        val inclination: TraitDetail,
        val shape: TraitDetail,
        val type: String,
        val typeDescription: String,
        val description: String,
    ) : PersonalityUiState
}

fun Personality.toPersonalityUiState() =
    PersonalityUiState.ResultUiState(
        size = this.traits.size,
        pressure = this.traits.pressure,
        inclination = this.traits.inclination,
        shape = this.traits.shape,
        type = this.type,
        typeDescription = this.typeDescription,
        description = this.description,
    )
