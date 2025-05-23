package com.analysis.domain.repository

import com.analysis.domain.model.Personality
import kotlinx.coroutines.flow.Flow

interface PersonalityAnalyzeRepository {
    fun executeAnalyze(
        imageUrl: String,
    ): Flow<Personality>
}
