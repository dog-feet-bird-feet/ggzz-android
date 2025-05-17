package com.analysis.data.remote.source

import android.util.Log
import com.analysis.data.remote.api.AnalysisApiService
import com.analysis.data.remote.dto.request.AppraisalRequest
import com.analysis.data.remote.dto.response.AnalysisResponse
import com.analysis.data.source.AnalysisDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnalysisDataSourceImpl @Inject constructor(
    private val analysisApiService: AnalysisApiService,
) : AnalysisDataSource {
    override fun executeAnalysis(appraisalRequest: AppraisalRequest): Flow<AnalysisResponse> {
        return flow {
            val res = analysisApiService.postAnalysis(appraisalRequest)
            Log.e("seogi", "AnalysisDataSource body: ${res.body()}")
            Log.e("seogi", "AnalysisDataSource code: ${res.code()}")
            Log.e("seogi", "AnalysisDataSource msg: ${res.message()}")
            Log.e("seogi", "AnalysisDataSource errBody: ${res.errorBody()?.toString()}")

            emit(
                res.body()
                    ?: throw IllegalArgumentException()
            )
        }
    }
}
