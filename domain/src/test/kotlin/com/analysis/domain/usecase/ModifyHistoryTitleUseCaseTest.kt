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

class ModifyHistoryTitleUseCaseTest {
    private val historyRepository: HistoryRepository = mockk()
    private val modifyHistoryTitleUseCase: ModifyHistoryTitleUseCase = ModifyHistoryTitleUseCase(historyRepository)

    @Test
    @DisplayName("히스토리 제목을 수정한다")
    fun modifyHistoryTitle() {
        runTest {
            // given
            coEvery { historyRepository.modifyHistoryTitle("1", "test") } returns flowOf(Unit)

            // when
            val actual = modifyHistoryTitleUseCase("1", "test")

            // then
            assertThat(actual.first()).isEqualTo(Unit)
        }
    }
}
