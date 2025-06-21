package com.analysis.domain.usecase

import com.analysis.domain.repository.LoginRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SignUpUseCase
@Inject
constructor(
    private val loginRepository: LoginRepository,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(
        email: String,
        password: String,
    ): Flow<Boolean> {
        return loginRepository.signUp(email, password).flatMapLatest { isSignUpSuccess ->
            if (isSignUpSuccess) {
                loginRepository.login(email, password)
            } else {
                flowOf(false)
            }
        }
    }
}
