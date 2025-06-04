package com.analysis.domain.usecase

import com.analysis.domain.model.Personality
import com.analysis.domain.model.TraitDetail
import com.analysis.domain.model.Traits
import com.analysis.domain.repository.PersonalityRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import okhttp3.MultipartBody
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PersonalityAnalyzeUseCaseTest {
    private val personalityRepository: PersonalityRepository = mockk()
    private val fakeImage: MultipartBody.Part = mockk()
    private val fakePersonality: Personality = Personality(
        id = 1, traits = Traits(
            size = TraitDetail(score = "", detail = ""),
            pressure = TraitDetail(
                score = "",
                detail = ""
            ),
            inclination = TraitDetail(
                score = "",
                detail = ""
            ),
            shape = TraitDetail(score = "", detail = "")
        ), type = "", typeDescription = "", description = ""
    )
    private val personalityAnalyzeUseCase: PersonalityAnalyzeUseCase =
        PersonalityAnalyzeUseCase(personalityRepository)

    @Test
    @DisplayName("성격 분석 후 결과를 가져온다")
    fun analysisPersonality() {
        runTest {
            // given
            coEvery { personalityRepository.uploadImage(fakeImage) } returns flowOf("")
            coEvery { personalityRepository.executeAnalyze("") } returns flowOf(fakePersonality)

            // when
            val actual = personalityAnalyzeUseCase(fakeImage)

            // then
            assertThat(actual.first()).isEqualTo(fakePersonality)
        }
    }
}
