package com.analysis.data.remote.source

import com.analysis.data.local.GgzzDataStore
import com.analysis.data.remote.api.LoginApiService
import com.analysis.data.remote.dto.request.LoginRequest
import com.analysis.data.remote.dto.request.SignUpRequest
import com.analysis.data.source.LoginDataSource
import com.analysis.data.util.errorMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(
    private val loginApiService: LoginApiService,
    private val dataStore: GgzzDataStore,
) : LoginDataSource {
    override fun hasAccessToken(): Flow<Boolean> {
        return flow {
            emit(dataStore.hasAccessToken())
        }
    }

    override fun signUp(signUpRequest: SignUpRequest): Flow<Boolean> {
        return flow {
            val response = loginApiService.postSignUp(signUpRequest)
            if (response.isSuccessful) {
                emit(true)
            } else {
                throw Throwable(response.errorMessage())
            }
        }
    }

    override fun checkEmail(email: String): Flow<Boolean> {
        return flow {
            val response = loginApiService.getEmailCheck(email)
            emit(response.isSuccessful)
        }
    }

    override fun login(loginRequest: LoginRequest): Flow<Boolean> {
        return flow {
            val response = loginApiService.postLogin(loginRequest)
            if (response.isSuccessful) {
                val authHeader = response.headers()["Authorization"]
                    ?: throw Throwable(response.message())
                val accessToken = authHeader.substringAfter("Bearer ").trim()
                emit(dataStore.setAccessToken(accessToken))
            } else {
                throw Throwable(response.errorMessage())
            }
        }
    }

    override fun logout(): Flow<Boolean> {
        return flow {
            emit(dataStore.clearAccessToken())
        }
    }
}
