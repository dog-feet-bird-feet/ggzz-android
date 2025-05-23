package com.analysis.data.source

import com.analysis.data.remote.dto.request.PersonalityAnalyzeRequest
import com.analysis.data.remote.dto.response.PersonalityResponse
import kotlinx.coroutines.flow.Flow

interface PersonalityAnalyzeDataSource {
    fun executeAnalyze(personalityAnalyzeRequest: PersonalityAnalyzeRequest): Flow<PersonalityResponse>
}
