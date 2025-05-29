package com.analysis.data.repository

import com.analysis.data.remote.dto.request.LoginRequest
import com.analysis.data.source.LoginDataSource
import com.analysis.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource,
) : LoginRepository {
    override fun login(email: String, password: String): Flow<Boolean> {
        return loginDataSource.login(LoginRequest(email, password))
    }
}
