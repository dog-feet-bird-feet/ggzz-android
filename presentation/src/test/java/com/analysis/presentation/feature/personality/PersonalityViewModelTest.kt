package com.analysis.presentation.feature.personality

import android.net.Uri
import com.analysis.domain.model.Personality
import com.analysis.domain.model.TraitDetail
import com.analysis.domain.model.Traits
import com.analysis.domain.usecase.PersonalityAnalyzeUseCase
import com.analysis.presentation.feature.personality.model.toPersonalityUiState
import com.analysis.presentation.rule.MainDispatcherRule
import com.analysis.presentation.util.ImageUtil
import io.mockk.coEvery
import io.mockk.every
import io.mockk.invoke
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import okhttp3.MultipartBody
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class PersonalityViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val fakePersonality: Personality = Personality(
        id = 1,
        traits = Traits(
            size = TraitDetail(score = "", detail = ""),
            pressure = TraitDetail(
                score = "",
                detail = "",
            ),
            inclination = TraitDetail(
                score = "",
                detail = "",
            ),
            shape = TraitDetail(score = "", detail = ""),
        ),
        type = "",
        typeDescription = "",
        description = "",
    )

    private val imageUtil: ImageUtil = mockk(relaxed = true)
    private val personalityAnalyzeUseCase: PersonalityAnalyzeUseCase = mockk()
    private val viewModel = PersonalityViewModel(imageUtil, personalityAnalyzeUseCase)

    @Test
    @DisplayName("이미지를 업로드 한다")
    fun updatePickedVerificationUri() {
        runTest {
            // given
            val uri = mockk<Uri>(relaxed = true)
            every { imageUtil.isValidFormat(uri) } returns true
            coEvery { imageUtil.analyzeImageHasTextWithKorean(uri) } returns flowOf(true)

            // when
            viewModel.updatePickedVerificationUri(uri)

            // then
            assertThat(viewModel.selectedImageUri.value).isEqualTo(uri)
        }
    }

    @Test
    @DisplayName("이미지를 제거 한다")
    fun removeVerificationUri() {
        // when
        viewModel.removeVerificationUri()

        // then
        assertThat(viewModel.selectedImageUri.value).isEqualTo(Uri.EMPTY)
    }

    @Test
    @DisplayName("성격분석 후 결과를 가져온다")
    fun executeAnalysis() {
        runTest {
            // given
            val image: MultipartBody.Part = mockk()

            every { imageUtil.buildMultiPart(any(), any()) } returns image
            coEvery { personalityAnalyzeUseCase(image) } returns flowOf(fakePersonality)

            // when
            viewModel.executeAnalysis()

            // then
            assertThat(viewModel.personalityUiState.value).isEqualTo(fakePersonality.toPersonalityUiState())
        }
    }
}
