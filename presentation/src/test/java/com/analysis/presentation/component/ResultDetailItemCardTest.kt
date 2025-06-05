package com.analysis.presentation.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.analysis.presentation.model.ResultIndicator
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ResultDetailItemCardTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `지표명이 화면에 나타난다`() {
        // given
        val resultIndicator = ResultIndicator.Similarity(0f)
        composeRule.setContent {
            ResultDetailItemCard(resultIndicator)
        }

        // then
        composeRule.onNodeWithText("유사도")
            .assertIsDisplayed()
    }

    @Test
    fun `지표수치가 화면에 나타난다`() {
        // given
        val resultIndicator = ResultIndicator.Similarity(43f)
        composeRule.setContent {
            ResultDetailItemCard(resultIndicator)
        }

        // then
        composeRule.onNodeWithText("43%")
            .assertIsDisplayed()
    }

    @Test
    fun `지표에 대한 설명이 화면에 나타난다`() {
        // given
        val resultIndicator = ResultIndicator.Similarity(43f)
        composeRule.setContent {
            ResultDetailItemCard(resultIndicator)
        }

        // then
        composeRule.onNodeWithText(resultIndicator.description)
            .assertIsDisplayed()
    }

}
