package com.analysis.domain.repository

import com.analysis.domain.model.HistoryDetail
import com.analysis.domain.model.History
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    fun fetchHistories(): Flow<List<History>>

    fun fetchHistoryDetail(id: String): Flow<HistoryDetail>

    fun modifyHistoryTitle(
        id: String,
        title: String,
    ): Flow<Unit>

    fun removeHistory(id: String): Flow<Unit>
}
