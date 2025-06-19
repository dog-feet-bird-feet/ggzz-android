package com.analysis.data.source

import com.analysis.data.remote.dto.request.LoginRequest
import com.analysis.data.remote.dto.request.SignUpRequest
import kotlinx.coroutines.flow.Flow

interface LoginDataSource {
    fun hasAccessToken(): Flow<Boolean>

    fun signUp(signUpRequest: SignUpRequest): Flow<Boolean>

    fun checkEmail(email: String): Flow<Boolean>

    fun login(loginRequest: LoginRequest): Flow<Boolean>

    fun logout(): Flow<Boolean>
}
