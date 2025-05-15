package com.analysis.data.remote.api

import com.analysis.data.remote.dto.request.HistoryRequest
import com.analysis.data.remote.dto.response.HistoryDetailResponse
import com.analysis.data.remote.dto.response.HistoryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query

interface HistoryApiService {
    @GET("/api/v1/history")
    suspend fun getHistories(): Response<List<HistoryResponse>>

    @GET("/api/v1/history/result")
    suspend fun getHistoryDetail(
        @Query("id") id: String,
    ): Response<HistoryDetailResponse>

    @PATCH("/api/v1/history/result")
    suspend fun patchHistory(
        @Query("id") id: String,
        @Body historyRequest: HistoryRequest,
    ): Response<Unit>

    @DELETE("/api/v1/history/result")
    suspend fun deleteHistory(
        @Query("id") id: String,
    ): Response<Unit>
}
