package com.analysis.presentation.feature.login

import com.analysis.domain.usecase.LoginUseCase
import com.analysis.presentation.rule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class LoginViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val loginUseCase: LoginUseCase = mockk()
    private val viewModel = LoginViewModel(loginUseCase)

    @Test
    @DisplayName("로그인을 시도한다")
    fun fetchHistories() {
        runTest {
            // given
            coEvery { loginUseCase("", "") } returns flow { emit(true) }

            // when
            viewModel.login("","")
            val actual = viewModel.isLoginSuccess

            // then
            assertThat(actual.first()).isEqualTo(true)
        }
    }
}
