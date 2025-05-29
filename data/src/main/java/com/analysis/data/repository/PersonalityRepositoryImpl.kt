package com.analysis.data.repository

import com.analysis.data.remote.dto.request.PersonalityAnalyzeRequest
import com.analysis.data.remote.dto.response.toPersonality
import com.analysis.data.source.PersonalityDataSource
import com.analysis.domain.model.Personality
import com.analysis.domain.repository.PersonalityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.MultipartBody
import javax.inject.Inject

class PersonalityRepositoryImpl
    @Inject
    constructor(
        private val personalityDataSource: PersonalityDataSource,
    ) : PersonalityRepository {
        override fun uploadImage(image: MultipartBody.Part): Flow<String> {
            return personalityDataSource.uploadImage(image).map { it.personalityUrl }
        }

        override fun executeAnalyze(imageUrl: String): Flow<Personality> {
            return personalityDataSource.executeAnalyze(PersonalityAnalyzeRequest(imageUrl))
                .map {
                    it.toPersonality()
                }
        }
    }
