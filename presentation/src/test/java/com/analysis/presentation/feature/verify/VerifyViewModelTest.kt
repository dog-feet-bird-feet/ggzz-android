package com.analysis.presentation.feature.verify

import android.net.Uri
import com.analysis.domain.model.AnalysisResult
import com.analysis.domain.usecase.AnalysisUseCase
import com.analysis.presentation.feature.verify.model.VerificationUiState
import com.analysis.presentation.feature.verify.model.toVerificationResultUiState
import com.analysis.presentation.rule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import okhttp3.MultipartBody
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class VerifyViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val fakeAnalysisUseCase: AnalysisUseCase = mockk()
    private val viewModel = VerifyViewModel(fakeAnalysisUseCase)

    @Test
    @DisplayName("초기 업로드 상태는 대조물 업로드이다.")
    fun isUploadStateDefaultComparisonUploadState() {
        assertThat(viewModel.uiState.value).isEqualTo(VerificationUiState.ComparisonUploadState)
    }

    @Test
    @DisplayName("업로드 상태가 대조물 업로드에서 검증물 업로드로 바뀐다")
    fun changeUploadStateToVerificationState() {
        // when
        viewModel.moveToVerificationUpload()

        // then
        assertThat(viewModel.uiState.value).isEqualTo(VerificationUiState.VerificationUploadState)
    }

    @Test
    @DisplayName("업로드 상태가 검증물 업로드에서 대조물 업로드로 바뀐다")
    fun changeUploadStateToComparisonState() {
        // given
        viewModel.moveToVerificationUpload()

        // when
        viewModel.moveToComparisonUpload()

        // then
        assertThat(viewModel.uiState.value).isEqualTo(VerificationUiState.ComparisonUploadState)
    }

    @Test
    @DisplayName("대조물들을 업로드한다")
    fun updatePickedComparisonUris() {
        // given
        val uris = listOf(mockk<Uri>(relaxed = true))

        // when
        viewModel.updatePickedComparisonUris(uris)

        // then
        assertThat(viewModel.selectedComparisonUris.value).isEqualTo(uris)
    }

    @Test
    @DisplayName("대조물을 제거한다")
    fun removeComparisonUri() {
        // given
        val uri1 = mockk<Uri>(relaxed = true)
        val uri2 = mockk<Uri>(relaxed = true)
        val uris = listOf(uri1, uri2)
        viewModel.updatePickedComparisonUris(uris)

        // when
        viewModel.removeComparisonUri(uri1)
        val actual = uris - uri1

        // then
        assertThat(viewModel.selectedComparisonUris.value).isEqualTo(actual)
    }

    @Test
    @DisplayName("검증물을 업로드한다")
    fun updatePickedVerificationUri() {
        // given
        val uri = mockk<Uri>(relaxed = true)

        // when
        viewModel.updatePickedVerificationUri(uri)

        // then
        assertThat(viewModel.selectedVerificationUri.value).isEqualTo(uri)
    }

    @Test
    @DisplayName("검증물을 제거한다")
    fun removeVerificationUri() {
        // given
        val uri = mockk<Uri>(relaxed = true)
        viewModel.updatePickedVerificationUri(uri)

        // when
        viewModel.removeVerificationUri()

        // then
        assertThat(viewModel.selectedVerificationUri.value).isEqualTo(null)
    }

    @Test
    @DisplayName("필적 감정을 진핸한다")
    fun executeAnalysis() {
        runTest {
            // given
            val image: MultipartBody.Part = mockk()
            val fakeAnalysisResult = AnalysisResult(1f, 1f, 1f)
            coEvery { fakeAnalysisUseCase(listOf(image), image) } returns flowOf(fakeAnalysisResult)

            // when
            viewModel.executeAnalysis(listOf(image), image)

            // then
            assertThat(viewModel.uiState.value).isEqualTo(fakeAnalysisResult.toVerificationResultUiState())
        }
    }
}
