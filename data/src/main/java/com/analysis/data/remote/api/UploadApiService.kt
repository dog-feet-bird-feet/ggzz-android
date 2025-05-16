package com.analysis.data.remote.api

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadApiService {
    @Multipart
    @POST("/api/v1/s3/upload/comparisons")
    suspend fun postComparisons(
        @Part("comparison-file") images: List<MultipartBody.Part>,
    ): Response<List<String>>

    @Multipart
    @POST("/api/v1/s3/upload/verification")
    suspend fun postVerification(
        @Part("verification-file") image: MultipartBody.Part,
    ): Response<String>
}
