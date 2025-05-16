package com.analysis.data.source

import com.analysis.data.remote.dto.request.AnalysisRequest
import com.analysis.data.remote.dto.response.AnalysisResponse
import kotlinx.coroutines.flow.Flow

interface AnalysisDataSource {
    fun executeAnalysis(
        analysisRequest: AnalysisRequest
    ): Flow<AnalysisResponse>
}
