package com.analysis.domain.repository

import com.analysis.domain.model.AnalysisResult
import kotlinx.coroutines.flow.Flow

interface AnalysisRepository {
    fun executeAnalysis(
        verificationImageUrl: String,
        comparisonImageUrls: List<String>,
    ): Flow<AnalysisResult>
}
