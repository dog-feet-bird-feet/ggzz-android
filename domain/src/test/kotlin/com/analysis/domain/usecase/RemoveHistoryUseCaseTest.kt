package com.analysis.domain.usecase

import com.analysis.domain.repository.HistoryRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RemoveHistoryUseCaseTest {
    private val historyRepository: HistoryRepository = mockk()
    private val removeHistoryUseCase: RemoveHistoryUseCase = RemoveHistoryUseCase(historyRepository)

    @Test
    @DisplayName("히스토리를 삭제한다")
    fun removeHistory() {
        runTest {
            // given
            coEvery { historyRepository.removeHistory("1") } returns flowOf(Unit)

            // when
            val actual = removeHistoryUseCase("1")

            // then
            assertThat(actual.first()).isEqualTo(Unit)
        }
    }
}
