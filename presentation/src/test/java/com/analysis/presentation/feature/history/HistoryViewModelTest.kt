package com.analysis.presentation.feature.history

import com.analysis.domain.model.AnalysisResult
import com.analysis.domain.model.History
import com.analysis.domain.usecase.AnalysisUseCase
import com.analysis.domain.usecase.FetchHistoriesUseCase
import com.analysis.domain.usecase.ModifyHistoryTitleUseCase
import com.analysis.domain.usecase.RemoveHistoryUseCase
import com.analysis.presentation.feature.verify.VerifyViewModel
import com.analysis.presentation.feature.verify.model.VerificationUiState
import com.analysis.presentation.feature.verify.model.toVerificationResultUiState
import com.analysis.presentation.rule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class HistoryViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val fetchHistoriesUseCase: FetchHistoriesUseCase = mockk()
    private val modifyHistoryTitleUseCase: ModifyHistoryTitleUseCase = mockk()
    private val removeHistoryUseCase: RemoveHistoryUseCase = mockk()
    private val viewModel =
        HistoryViewModel(fetchHistoriesUseCase, modifyHistoryTitleUseCase, removeHistoryUseCase)

    @Before
    fun setUp(){
        val fakeHistory = History("", "", "", "")
        coEvery { fetchHistoriesUseCase() } returns flow { emit(listOf(fakeHistory)) }
    }

    @Test
    @DisplayName("히스토리 목록을 가져온다")
    fun fetchHistories() {
        runTest {
            // given
            val fakeHistory = History("1", "test", "test", "test")
            coEvery { fetchHistoriesUseCase() } returns flow { emit(listOf(fakeHistory)) }

            // when
            viewModel.fetchHistories()
            val actual = viewModel.histories.drop(1)

            // then
            assertThat(actual.first()).isEqualTo(listOf(fakeHistory))
        }
    }

    @Test
    @DisplayName("히스토리 제목 수정시 성공하면 true를 가져온다")
    fun modifyHistoryTitleSuccess() {
        runTest {
            // given
            coEvery { modifyHistoryTitleUseCase("","") } returns flow { emit(Unit) }

            // when
            viewModel.modifyHistoryTitle("","")
            val actual = viewModel.isModifySuccess.first()

            // then
            assertThat(actual).isEqualTo(true)
        }
    }

    @Test
    @DisplayName("히스토리 제목 수정시 실패하면 false를 가져온다")
    fun modifyHistoryTitleFail() {
        runTest {
            // given
            coEvery { modifyHistoryTitleUseCase("","") } returns flow { throw Throwable() }

            // when
            viewModel.modifyHistoryTitle("","")
            val actual = viewModel.isModifySuccess.first()

            // then
            assertThat(actual).isEqualTo(false)
        }
    }

    @Test
    @DisplayName("히스토리 삭제 시 성공하면 true를 가져온다")
    fun removeHistorySuccess() {
        runTest {
            // given
            coEvery { removeHistoryUseCase("") } returns flow { emit(Unit) }

            // when
            viewModel.removeHistory("")
            val actual = viewModel.isRemoveSuccess.first()

            // then
            assertThat(actual).isEqualTo(true)
        }
    }

    @Test
    @DisplayName("히스토리 삭제 시 실패하면 false를 가져온다")
    fun removeHistoryFail() {
        runTest {
            // given
            coEvery { removeHistoryUseCase("") } returns flow { throw Throwable() }

            // when
            viewModel.removeHistory("")
            val actual = viewModel.isRemoveSuccess.first()

            // then
            assertThat(actual).isEqualTo(false)
        }
    }
}
