package com.analysis.data.remote.api

import com.analysis.data.remote.dto.request.LoginRequest
import com.analysis.data.remote.dto.request.SignUpRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApiService {
    @POST("/api/v1/login")
    suspend fun postLogin(
        @Body loginRequest: LoginRequest,
    ): Response<ResponseBody>

    @POST("/api/v1/signup")
    suspend fun postSignUp(
        @Body signUpRequest: SignUpRequest,
    ): Response<Unit>

    @GET("api/v1/check-email")
    suspend fun getEmailCheck(
        @Query("email") email: String,
    ): Response<Boolean>
}
