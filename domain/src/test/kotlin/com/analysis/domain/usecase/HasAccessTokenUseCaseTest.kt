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

class HasAccessTokenUseCaseTest {
    private val loginRepository: LoginRepository = mockk()
    private val hasAccessTokenUseCase: HasAccessTokenUseCase = HasAccessTokenUseCase(loginRepository)

    @Test
    @DisplayName("AccessToken 여부를 가져온다")
    fun getAccessTokenStatus() {
        runTest {
            // given
            coEvery { loginRepository.hasAccessToken() } returns flowOf(true)

            // when
            val actual = hasAccessTokenUseCase()

            // then
            assertThat(actual.first()).isEqualTo(true)
        }
    }
}
