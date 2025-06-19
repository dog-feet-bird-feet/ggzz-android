package com.analysis.domain.repository

import com.analysis.domain.model.Personality
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface PersonalityRepository {
    fun uploadImage(image: MultipartBody.Part): Flow<String>

    fun executeAnalyze(imageUrl: String): Flow<Personality>
}
