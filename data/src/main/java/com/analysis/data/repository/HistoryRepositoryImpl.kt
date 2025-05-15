package com.analysis.data.repository

import com.analysis.data.remote.dto.request.HistoryRequest
import com.analysis.data.remote.dto.response.toAnalysisResult
import com.analysis.data.remote.dto.response.toHistory
import com.analysis.data.source.HistoryDataSource
import com.analysis.domain.model.AnalysisResult
import com.analysis.domain.model.History
import com.analysis.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HistoryRepositoryImpl
    @Inject
    constructor(
        private val historyDataSource: HistoryDataSource,
    ) : HistoryRepository {
        override fun fetchHistories(): Flow<List<History>> {
            return historyDataSource.fetchHistories().map {
                it.map { it.toHistory() }
            }
        }

        override fun fetchHistoryDetail(id: String): Flow<AnalysisResult> {
            return historyDataSource.fetchHistoryDetail(id).map { it.toAnalysisResult() }
        }

        override fun modifyHistoryTitle(
            id: String,
            title: String,
        ): Flow<Unit> {
            return historyDataSource.modifyHistoryTitle(id, HistoryRequest(title))
        }

        override fun removeHistory(id: String): Flow<Unit> {
            return historyDataSource.removeHistory(id)
        }
    }
