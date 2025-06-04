package com.analysis.domain.usecase

import com.analysis.domain.model.History
import com.analysis.domain.model.HistoryDetail
import com.analysis.domain.repository.HistoryRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FetchHistoryDetailUseCaseTest {
    private val historyRepository: HistoryRepository = mockk()
    private val fakeHistoryDetail: HistoryDetail = HistoryDetail(
        id = "1",
        title = "test",
        similarity = 1f,
        pressure = 1f,
        inclination = 1f,
        verificationImgUrl = "test",
        createdAt = "test"
    )
    private val fetchHistoryDetailUseCase: FetchHistoryDetailUseCase = FetchHistoryDetailUseCase(historyRepository)

    @Test
    @DisplayName("히스토리 상세를 가져온다")
    fun fetchHistoryDetail() {
        runTest {
            // given
            coEvery { historyRepository.fetchHistoryDetail("") } returns flowOf(fakeHistoryDetail)

            // when
            val actual = fetchHistoryDetailUseCase("")

            // then
            assertThat(actual.first()).isEqualTo(fakeHistoryDetail)
        }
    }
}
