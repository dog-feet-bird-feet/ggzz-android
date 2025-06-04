package com.analysis.data.remote.source

import com.analysis.data.remote.api.AnalysisApiService
import com.analysis.data.remote.dto.request.AppraisalRequest
import com.analysis.data.remote.dto.response.AnalysisResponse
import com.analysis.data.source.AnalysisDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnalysisDataSourceImpl
    @Inject
    constructor(
        private val analysisApiService: AnalysisApiService,
    ) : AnalysisDataSource {
        override fun executeAnalysis(appraisalRequest: AppraisalRequest): Flow<AnalysisResponse> {
            return flow {
                val response = analysisApiService.postAnalysis(appraisalRequest)
                emit(
                   response .body() ?: throw Throwable(response.message()),
                )
            }
        }
    }
