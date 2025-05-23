package com.analysis.domain.usecase

import com.analysis.domain.model.Personality
import com.analysis.domain.repository.PersonalityRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import okhttp3.MultipartBody
import javax.inject.Inject

class PersonalityAnalyzeUseCase @Inject constructor(
    private val personalityRepository: PersonalityRepository,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(
        image: MultipartBody.Part,
    ): Flow<Personality> {
        return personalityRepository.uploadImage(image)
            .flatMapLatest { imageUrl ->
                personalityRepository.executeAnalyze(imageUrl)
            }
    }
}

