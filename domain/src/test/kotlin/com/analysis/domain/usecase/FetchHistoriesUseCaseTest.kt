package com.analysis.domain.usecase

import com.analysis.domain.model.History
import com.analysis.domain.repository.HistoryRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FetchHistoriesUseCaseTest {
    private val historyRepository: HistoryRepository = mockk()

    private val fakeHistory: History = History("1", "test", "test", "test")
    private val fetchHistoriesUseCase: FetchHistoriesUseCase = FetchHistoriesUseCase(historyRepository)

    @Test
    @DisplayName("히스토리 목록을 가져온다")
    fun fetchHistories() {
        runTest {
            // given
            coEvery { historyRepository.fetchHistories() } returns flowOf(listOf(fakeHistory))

            // when
            val actual = fetchHistoriesUseCase()

            // then
            assertThat(actual.first()).isEqualTo(listOf(fakeHistory))
        }
    }
}
