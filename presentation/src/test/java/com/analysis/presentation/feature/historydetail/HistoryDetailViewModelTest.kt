package com.analysis.presentation.feature.historydetail

import androidx.lifecycle.SavedStateHandle
import com.analysis.domain.model.HistoryDetail
import com.analysis.domain.usecase.FetchHistoryDetailUseCase
import com.analysis.presentation.feature.historydetail.model.toHistoryDetailUiState
import com.analysis.presentation.rule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class HistoryDetailViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val fakeHistoryDetail: HistoryDetail = HistoryDetail(
        id = "1",
        title = "test",
        similarity = 1f,
        pressure = 1f,
        inclination = 1f,
        verificationImgUrl = "test",
        createdAt = "test",
    )

    private val fetchHistoryDetailUseCase: FetchHistoryDetailUseCase = mockk()
    private val savedStateHandle: SavedStateHandle = mockk()
    private lateinit var viewModel: HistoryDetailViewModel

    @Before
    fun setUp() {
        every { savedStateHandle.get<String>("historyId") } returns "1"
        coEvery { fetchHistoryDetailUseCase("1") } returns flow { emit(fakeHistoryDetail) }
        viewModel = HistoryDetailViewModel(savedStateHandle, fetchHistoryDetailUseCase)
    }

    @Test
    @DisplayName("히스토리 상세를 가져온다")
    fun fetchHistories() {
        runTest {
            // when
            val actual = viewModel.history.first()

            // then
            assertThat(actual).isEqualTo(fakeHistoryDetail.toHistoryDetailUiState())
        }
    }
}
