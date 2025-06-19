package com.analysis.domain.usecase

import com.analysis.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckEmailUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {
    operator fun invoke(
        email: String,
    ): Flow<Boolean> {
        return loginRepository.checkEmail(email)
    }
}
