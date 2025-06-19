package com.analysis.data.remote.source

import com.analysis.data.remote.api.HistoryApiService
import com.analysis.data.remote.dto.request.HistoryRequest
import com.analysis.data.remote.dto.response.HistoryDetailResponse
import com.analysis.data.remote.dto.response.HistoryResponse
import com.analysis.data.source.HistoryDataSource
import com.analysis.data.util.errorMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HistoryDataSourceImpl
    @Inject
    constructor(
        private val historyApiService: HistoryApiService,
    ) : HistoryDataSource {
        override fun fetchHistories(): Flow<List<HistoryResponse>> {
            return flow {
                val response = historyApiService.getHistories()
                emit(response.body() ?: throw Throwable(response.errorMessage()))
            }
        }

        override fun fetchHistoryDetail(id: String): Flow<HistoryDetailResponse> {
            return flow {
                val response = historyApiService.getHistoryDetail(id)
                emit(response.body() ?: throw Throwable(response.errorMessage()))
            }
        }

        override fun modifyHistoryTitle(
            id: String,
            historyRequest: HistoryRequest,
        ): Flow<Unit> {
            return flow {
                val response = historyApiService.patchHistory(id, historyRequest)
                emit(
                    response.body() ?: throw Throwable(response.errorMessage()),
                )
            }
        }

        override fun removeHistory(id: String): Flow<Unit> {
            return flow {
                val response = historyApiService.deleteHistory(id)
                emit(response.body() ?: throw Throwable(response.errorMessage()))
            }
        }
    }
