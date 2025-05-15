package com.analysis.presentation.feature.verify

import android.net.Uri
import com.analysis.presentation.feature.verify.model.UploadState
import com.analysis.presentation.rule.MainDispatcherRule
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class VerifyViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val viewModel = VerifyViewModel()

    @Test
    @DisplayName("초기 업로드 상태는 대조물 업로드이다.")
    fun isUploadStateDefaultComparisonUploadState() {
        assertThat(viewModel.uploadState.value).isEqualTo(UploadState.ComparisonUploadState)
    }

    @Test
    @DisplayName("업로드 상태가 대조물 업로드에서 검증물 업로드로 바뀐다")
    fun changeUploadStateToVerificationUploadState() {
        // when
        viewModel.changeUploadState()

        // then
        assertThat(viewModel.uploadState.value).isEqualTo(UploadState.VerificationUploadState)
    }

    @Test
    @DisplayName("업로드 상태가 검증물 업로드에서 대조물 업로드로 바뀐다")
    fun changeUploadStateToComparisonUploadState() {
        // given
        viewModel.changeUploadState()

        // when
        viewModel.changeUploadState()

        // then
        assertThat(viewModel.uploadState.value).isEqualTo(UploadState.ComparisonUploadState)
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
        viewModel.removeVerificationUri(uri)

        // then
        assertThat(viewModel.selectedVerificationUri.value).isEqualTo(null)
    }
}
