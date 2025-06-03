package com.analysis.data.remote.api

import com.analysis.data.remote.dto.request.LoginRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("/api/v1/login")
    suspend fun postLogin(
        @Body loginRequest: LoginRequest,
    ): Response<ResponseBody>
}
