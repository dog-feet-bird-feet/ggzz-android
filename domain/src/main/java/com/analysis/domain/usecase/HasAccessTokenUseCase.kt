package com.analysis.domain.usecase

import com.analysis.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HasAccessTokenUseCase
    @Inject
    constructor(
        private val loginRepository: LoginRepository,
    ) {
        operator fun invoke(): Flow<Boolean> {
            return loginRepository.hasAccessToken()
        }
    }
