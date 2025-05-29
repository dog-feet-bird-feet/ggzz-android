package com.analysis.data.source

import com.analysis.data.remote.dto.request.LoginRequest
import kotlinx.coroutines.flow.Flow

interface LoginDataSource{
    fun login(loginRequest: LoginRequest): Flow<Boolean>
}
