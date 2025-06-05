package com.analysis.presentation.component

import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PhotoPickerCardTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `기본값은 단일 선택이다`() {
        // given
        composeRule.setContent {
            PhotoPickerCard()
        }

        // then
        composeRule.onNodeWithText("(0/1)")
            .assertIsNotDisplayed()
    }

    @Test
    fun `최대 선택 수가 2 이상일 경우 다중 선택이다`() {
        // given
        composeRule.setContent {
            PhotoPickerCard(
                maxSelectable = 2,
                pickedPhotoCount = 1,
            )
        }

        // then
        composeRule.onNodeWithText("(1/2)")
            .assertIsNotDisplayed()
    }
}
