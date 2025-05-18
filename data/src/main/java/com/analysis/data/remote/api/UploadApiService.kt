package com.analysis.data.remote.api

import com.analysis.data.remote.dto.response.ComparisonsResponse
import com.analysis.data.remote.dto.response.VerificationResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadApiService {
    @Multipart
    @POST("/api/v1/s3/upload/comparisons")
    suspend fun postComparisons(
        @Part images: List<MultipartBody.Part>,
    ): Response<ComparisonsResponse>

    @Multipart
    @POST("/api/v1/s3/upload/verification")
    suspend fun postVerification(
        @Part image: MultipartBody.Part,
    ): Response<VerificationResponse>
}
