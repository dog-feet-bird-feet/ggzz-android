package com.analysis.domain.usecase

import com.analysis.domain.model.AnalysisResult
import com.analysis.domain.repository.AnalysisRepository
import com.analysis.domain.repository.UploadRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import okhttp3.MultipartBody
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class AnalysisUseCaseTest {
    private val uploadRepository: UploadRepository = mockk()
    private val analysisRepository: AnalysisRepository = mockk()
    private val fakeMultiPart: MultipartBody.Part = mockk()
    private val fakeAnalysisResult: AnalysisResult = AnalysisResult(1f, 1f, 1f)
    private val analysisUseCase: AnalysisUseCase =
        AnalysisUseCase(uploadRepository, analysisRepository)

    @Test
    @DisplayName("필적 감정 후 결과를 가져온다")
    fun executeAnalysisAndGetResult() {
        runTest {
            // given
            coEvery { uploadRepository.saveComparisons(listOf(fakeMultiPart)) } returns flowOf(listOf(""))

            coEvery { uploadRepository.saveVerification(fakeMultiPart) } returns flowOf("")

            coEvery { analysisRepository.executeAnalysis("", listOf("")) } returns flowOf(fakeAnalysisResult)

            // when
            val actual = analysisUseCase(listOf(fakeMultiPart), fakeMultiPart)

            // then
            assertThat(actual.first()).isEqualTo(fakeAnalysisResult)
        }
    }
}
