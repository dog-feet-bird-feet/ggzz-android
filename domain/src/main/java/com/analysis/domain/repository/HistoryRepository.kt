package com.analysis.domain.repository

import com.analysis.domain.model.AnalysisResult
import com.analysis.domain.model.History
import kotlinx.coroutines.flow.Flow


interface HistoryRepository {
    fun fetchHistories(): Flow<List<History>>

    fun fetchHistoryDetail(id: String): Flow<AnalysisResult>

    fun modifyHistoryTitle(id: String, title: String): Flow<Unit>

    fun removeHistory(id: String): Flow<Unit>
}
