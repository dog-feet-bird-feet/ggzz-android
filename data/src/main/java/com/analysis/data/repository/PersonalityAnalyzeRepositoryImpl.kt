package com.analysis.data.repository

import com.analysis.data.remote.dto.request.PersonalityAnalyzeRequest
import com.analysis.data.remote.dto.response.toPersonality
import com.analysis.data.source.PersonalityAnalyzeDataSource
import com.analysis.domain.model.Personality
import com.analysis.domain.repository.PersonalityAnalyzeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonalityAnalyzeRepositoryImpl @Inject constructor(
    private val personalityAnalyzeDataSource: PersonalityAnalyzeDataSource,
) : PersonalityAnalyzeRepository {
    override fun executeAnalyze(imageUrl: String): Flow<Personality> {
        return personalityAnalyzeDataSource.executeAnalyze(PersonalityAnalyzeRequest(imageUrl))
            .map {
                it.toPersonality()
            }
    }
}
