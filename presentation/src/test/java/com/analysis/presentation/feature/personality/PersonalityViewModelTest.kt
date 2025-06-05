package com.analysis.presentation.feature.personality

import android.net.Uri
import com.analysis.domain.model.Personality
import com.analysis.domain.model.TraitDetail
import com.analysis.domain.model.Traits
import com.analysis.domain.usecase.PersonalityAnalyzeUseCase
import com.analysis.presentation.feature.personality.model.toPersonalityUiState
import com.analysis.presentation.rule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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
    private val personalityAnalyzeUseCase: PersonalityAnalyzeUseCase = mockk()
    private val viewModel = PersonalityViewModel(personalityAnalyzeUseCase)

    @Test
    @DisplayName("이미지를 업로드 한다")
    fun updatePickedVerificationUri() {
        // given
        val uri = mockk<Uri>(relaxed = true)

        // when
        viewModel.updatePickedVerificationUri(uri)

        // then
        assertThat(viewModel.selectedImageUri.value).isEqualTo(uri)
    }

    @Test
    @DisplayName("이미지를 제거 한다")
    fun removeVerificationUri() {
        // given
        val uri = mockk<Uri>(relaxed = true)

        // when
        viewModel.removeVerificationUri()

        // then
        assertThat(viewModel.selectedImageUri.value).isEqualTo(null)
    }

    @Test
    @DisplayName("성격분석 후 결과를 가져온다")
    fun executeAnalysis() {
        runTest {
            // given
            val image: MultipartBody.Part = mockk()
            coEvery { personalityAnalyzeUseCase(image) } returns flow { emit(fakePersonality) }

            // when
            viewModel.executeAnalysis(image)
            val actual = viewModel.personalityUiState

            // then
            assertThat(actual.first()).isEqualTo(fakePersonality.toPersonalityUiState())
        }
    }
}
