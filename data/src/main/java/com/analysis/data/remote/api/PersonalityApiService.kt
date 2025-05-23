package com.analysis.data.remote.api

import com.analysis.data.remote.dto.request.PersonalityAnalyzeRequest
import com.analysis.data.remote.dto.response.PersonalityResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PersonalityApiService {
    @POST("/api/v1/personality/analyze")
    suspend fun postPersonalityAnalyze(
        @Body personalityAnalyzeRequest: PersonalityAnalyzeRequest,
    ): Response<PersonalityResponse>
}
