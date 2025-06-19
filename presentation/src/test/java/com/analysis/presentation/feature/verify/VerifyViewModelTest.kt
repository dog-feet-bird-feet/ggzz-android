package com.analysis.presentation.feature.verify

import android.net.Uri
import com.analysis.domain.model.AnalysisResult
import com.analysis.domain.usecase.AnalysisUseCase
import com.analysis.presentation.feature.verify.model.VerificationUiState
import com.analysis.presentation.feature.verify.model.toVerificationResultUiState
import com.analysis.presentation.rule.MainDispatcherRule
import com.analysis.presentation.util.ImageUtil
import io.mockk.coEvery
import io.mockk.every
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

    private val imageUtil: ImageUtil = mockk()
    private val fakeAnalysisUseCase: AnalysisUseCase = mockk()
    private val viewModel = VerifyViewModel(imageUtil, fakeAnalysisUseCase)

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
        runTest {
            // given
            val uri = mockk<Uri>(relaxed = true)
            val uris = listOf(uri)
            every { imageUtil.isValidFormat(uri) } returns true
            coEvery { imageUtil.analyzeImageHasTextWithKorean(uri) } returns flowOf(true)

            // when
            viewModel.updatePickedComparisonUris(uris)

            // then
            assertThat(viewModel.selectedComparisonUris.value).isEqualTo(uris)
        }
    }

    @Test
    @DisplayName("대조물을 제거한다")
    fun removeComparisonUri() {
        runTest {
            // given
            val uri1 = mockk<Uri>(relaxed = true)
            val uri2 = mockk<Uri>(relaxed = true)
            val uris = listOf(uri1, uri2)

            every { imageUtil.isValidFormat(any()) } returns true
            coEvery { imageUtil.analyzeImageHasTextWithKorean(any()) } returns flowOf(true)
            viewModel.updatePickedComparisonUris(uris)

            // when
            viewModel.removeComparisonUri(uri1)
            val actual = uris - uri1

            // then
            assertThat(viewModel.selectedComparisonUris.value).isEqualTo(actual)
        }
    }

    @Test
    @DisplayName("검증물을 업로드한다")
    fun updatePickedVerificationUri() {
        runTest {
            // given
            val uri = mockk<Uri>(relaxed = true)
            every { imageUtil.isValidFormat(uri) } returns true
            coEvery { imageUtil.analyzeImageHasTextWithKorean(uri) } returns flowOf(true)

            // when
            viewModel.updatePickedVerificationUri(uri)

            // then
            assertThat(viewModel.selectedVerificationUri.value).isEqualTo(uri)
        }
    }

    @Test
    @DisplayName("검증물을 제거한다")
    fun removeVerificationUri() {
        runTest {
            // given
            val uri = mockk<Uri>(relaxed = true)
            every { imageUtil.isValidFormat(uri) } returns true
            coEvery { imageUtil.analyzeImageHasTextWithKorean(uri) } returns flowOf(true)
            viewModel.updatePickedVerificationUri(uri)

            // when
            viewModel.removeVerificationUri()

            // then
            assertThat(viewModel.selectedVerificationUri.value).isEqualTo(Uri.EMPTY)
        }
    }

    @Test
    @DisplayName("필적 감정을 진핸한다")
    fun executeAnalysis() {
        runTest {
            // given
            val image: MultipartBody.Part = mockk()
            val fakeAnalysisResult = AnalysisResult(1f, 1f, 1f)

            every { imageUtil.buildMultiPart(any(), any()) } returns image
            every { imageUtil.buildMultiParts(any(), any()) } returns listOf(image)

            coEvery { fakeAnalysisUseCase(listOf(image), image) } returns flowOf(fakeAnalysisResult)

            // when
            viewModel.executeAnalysis()

            // then
            assertThat(viewModel.uiState.value).isEqualTo(fakeAnalysisResult.toVerificationResultUiState())
        }
    }
}
