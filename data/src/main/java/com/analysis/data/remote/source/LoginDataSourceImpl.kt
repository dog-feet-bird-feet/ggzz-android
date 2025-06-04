package com.analysis.data.remote.source

import com.analysis.data.local.GgzzDataStore
import com.analysis.data.remote.api.LoginApiService
import com.analysis.data.remote.dto.request.LoginRequest
import com.analysis.data.source.LoginDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginDataSourceImpl
    @Inject
    constructor(
        private val loginApiService: LoginApiService,
        private val dataStore: GgzzDataStore,
    ) : LoginDataSource {
        override fun hasAccessToken(): Flow<Boolean> {
            return flow {
                emit(dataStore.hasAccessToken())
            }
        }

        override fun login(loginRequest: LoginRequest): Flow<Boolean> {
            return flow {
                val response = loginApiService.postLogin(loginRequest)
                val authHeader = response.headers()["Authorization"]
                    ?: throw Throwable(response.message())
                val accessToken = authHeader.substringAfter("Bearer ").trim()
                emit(dataStore.setAccessToken(accessToken))
            }
        }
    }
