package com.analysis.data.remote.source

import com.analysis.data.remote.api.AnalysisApiService
import com.analysis.data.remote.dto.request.AnalysisRequest
import com.analysis.data.remote.dto.response.AnalysisResponse
import com.analysis.data.source.AnalysisDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnalysisDataSourceImpl @Inject constructor(
    private val analysisApiService: AnalysisApiService,
) : AnalysisDataSource {
    override fun executeAnalysis(analysisRequest: AnalysisRequest): Flow<AnalysisResponse> {
        return flow {
            emit(
                analysisApiService.postAnalysis(analysisRequest).body()
                    ?: throw IllegalArgumentException()
            )
        }
    }
}
