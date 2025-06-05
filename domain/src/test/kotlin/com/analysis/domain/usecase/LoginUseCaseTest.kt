package com.analysis.domain.usecase

import com.analysis.domain.repository.LoginRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LoginUseCaseTest {
    private val loginRepository: LoginRepository = mockk()
    private val loginUseCase: LoginUseCase = LoginUseCase(loginRepository)

    @Test
    @DisplayName("로그인 시도 후 성공 여부를 가져온다")
    fun login() {
        runTest {
            // given
            coEvery { loginRepository.login("", "") } returns flowOf(false)

            // when
            val actual = loginUseCase("", "")

            // then
            assertThat(actual.first()).isEqualTo(false)
        }
    }
}
