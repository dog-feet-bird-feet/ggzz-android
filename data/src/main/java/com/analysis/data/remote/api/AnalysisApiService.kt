package com.analysis.data.remote.api

import com.analysis.data.remote.dto.request.AnalysisRequest
import com.analysis.data.remote.dto.response.AnalysisResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AnalysisApiService {
    @POST("/appraisal")
    suspend fun postAnalysis(
        @Body analysisRequest: AnalysisRequest
    ): Response<AnalysisResponse>
}
