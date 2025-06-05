package com.analysis.presentation.feature.main

import com.analysis.domain.usecase.HasAccessTokenUseCase
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

class MainViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val hasAccessTokenUseCase: HasAccessTokenUseCase = mockk()
    private val viewModel = MainViewModel(hasAccessTokenUseCase)

    @Test
    @DisplayName("AccessToken 여부를 확인한다")
    fun getAccessTokenStatus() {
        runTest {
            // given
            coEvery { hasAccessTokenUseCase() } returns flow { emit(true) }

            // when
            viewModel.getAccessTokenStatus()
            val actual = viewModel.hasAccessToken

            // then
            assertThat(actual.first()).isEqualTo(true)
        }
    }
}
