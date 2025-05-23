package com.analysis.domain.usecase

import com.analysis.domain.model.Personality
import com.analysis.domain.repository.PersonalityAnalyzeRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import javax.inject.Inject

class PersonalityAnalyzeUseCase @Inject constructor(
    private val personalityAnalyzeRepository: PersonalityAnalyzeRepository,
){
    operator fun invoke(
        image: MultipartBody.Part,
    ): Flow<Personality> {
        
    }
}
