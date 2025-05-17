package com.analysis.data.repository

import android.util.Log
import com.analysis.data.remote.dto.request.AppraisalRequest
import com.analysis.data.remote.dto.response.toAnalysisResult
import com.analysis.data.source.AnalysisDataSource
import com.analysis.domain.model.AnalysisResult
import com.analysis.domain.repository.AnalysisRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnalysisRepositoryImpl @Inject constructor(
    private val analysisDataSource: AnalysisDataSource,
) : AnalysisRepository {
    override fun executeAnalysis(
        verificationImageUrl: String,
        comparisonImageUrls: List<String>,
    ): Flow<AnalysisResult> {
        return analysisDataSource.executeAnalysis(
            AppraisalRequest(verificationImageUrl, comparisonImageUrls)
        ).map {
            it.toAnalysisResult()
        }
    }
}
