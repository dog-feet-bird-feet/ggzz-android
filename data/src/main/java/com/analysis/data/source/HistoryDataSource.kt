package com.analysis.data.source

import com.analysis.data.remote.dto.request.HistoryRequest
import com.analysis.data.remote.dto.response.HistoryDetailResponse
import com.analysis.data.remote.dto.response.HistoryResponse
import kotlinx.coroutines.flow.Flow

interface HistoryDataSource {
    fun fetchHistories(): Flow<List<HistoryResponse>>

    fun fetchHistoryDetail(id: String): Flow<HistoryDetailResponse>

    fun modifyHistoryTitle(
        id: String,
        historyRequest: HistoryRequest,
    ): Flow<Unit>

    fun removeHistory(id: String): Flow<Unit>
}
