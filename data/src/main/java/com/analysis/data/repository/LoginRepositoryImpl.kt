package com.analysis.data.repository

import com.analysis.data.remote.dto.request.LoginRequest
import com.analysis.data.remote.dto.request.SignUpRequest
import com.analysis.data.source.LoginDataSource
import com.analysis.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource,
) : LoginRepository {
    override fun hasAccessToken(): Flow<Boolean> {
        return loginDataSource.hasAccessToken()
    }

    override fun signUp(email: String, password: String): Flow<Boolean> {
        return loginDataSource.signUp(SignUpRequest(email, password))
    }

    override fun checkEmail(email: String): Flow<Boolean> {
        return loginDataSource.checkEmail(email)
    }

    override fun login(
        email: String,
        password: String,
    ): Flow<Boolean> {
        return loginDataSource.login(LoginRequest(email, password))
    }

    override fun logout(): Flow<Boolean> {
        return loginDataSource.logout()
    }
}
