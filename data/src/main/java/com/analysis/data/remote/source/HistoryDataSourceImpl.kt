package com.analysis.data.remote.source

import com.analysis.data.remote.api.HistoryApiService
import com.analysis.data.remote.dto.request.HistoryRequest
import com.analysis.data.remote.dto.response.HistoryDetailResponse
import com.analysis.data.remote.dto.response.HistoryResponse
import com.analysis.data.source.HistoryDataSource
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
                emit(historyApiService.getHistories().body() ?: throw IllegalArgumentException())
            }
        }

        override fun fetchHistoryDetail(id: String): Flow<HistoryDetailResponse> {
            return flow {
                emit(historyApiService.getHistoryDetail(id).body() ?: throw IllegalArgumentException())
            }
        }

        override fun modifyHistoryTitle(
            id: String,
            historyRequest: HistoryRequest,
        ): Flow<Unit> {
            return flow {
                emit(
                    historyApiService.patchHistory(id, historyRequest).body()
                        ?: throw IllegalArgumentException(),
                )
            }
        }

        override fun removeHistory(id: String): Flow<Unit> {
            return flow {
                emit(historyApiService.deleteHistory(id).body() ?: throw IllegalArgumentException())
            }
        }
    }
