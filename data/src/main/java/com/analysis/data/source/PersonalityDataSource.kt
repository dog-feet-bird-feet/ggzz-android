package com.analysis.data.source

import com.analysis.data.remote.dto.request.PersonalityAnalyzeRequest
import com.analysis.data.remote.dto.response.PersonalityImageResponse
import com.analysis.data.remote.dto.response.PersonalityResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface PersonalityDataSource {
    fun uploadImage(image: MultipartBody.Part): Flow<PersonalityImageResponse>

    fun executeAnalyze(personalityAnalyzeRequest: PersonalityAnalyzeRequest): Flow<PersonalityResponse>
}
