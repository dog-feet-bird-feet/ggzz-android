package com.analysis.data.remote.api

import com.analysis.data.remote.dto.request.PersonalityAnalyzeRequest
import com.analysis.data.remote.dto.response.PersonalityImageResponse
import com.analysis.data.remote.dto.response.PersonalityResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PersonalityApiService {
    @POST("/api/v1/personality/analyze")
    suspend fun postPersonalityAnalyze(
        @Body personalityAnalyzeRequest: PersonalityAnalyzeRequest,
    ): Response<PersonalityResponse>

    @Multipart
    @POST("/api/v1/personality/upload")
    suspend fun postUploadImage(
        @Part image: MultipartBody.Part,
    ): Response<PersonalityImageResponse>
}
