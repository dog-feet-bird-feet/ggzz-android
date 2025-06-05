package com.analysis.presentation.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.analysis.presentation.model.DropMenuItem
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GgzzDropMenuButtonTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `클릭 시 드롭메뉴가 보인다`() {
        // given
        val items = listOf(DropMenuItem("수정하기", {}), DropMenuItem("삭제하기", {}))

        composeRule.setContent {
            GgzzDropMenuButton(items = items)
        }

        // when
        composeRule.onNodeWithTag("DropMenuButton")
            .performClick()

        // then
        composeRule.onNodeWithText("수정하기")
            .assertIsDisplayed()

        composeRule.onNodeWithText("삭제하기")
            .assertIsDisplayed()
    }

    @Test
    fun `드롭 메뉴를 클릭하면 onClick이 실행된다`() {
        // given
        var counter = 0
        val items = listOf(DropMenuItem("수정하기", { counter++ }))

        composeRule.setContent {
            GgzzDropMenuButton(items = items)
        }

        // when
        composeRule.onNodeWithTag("DropMenuButton")
            .performClick()

        composeRule.onNodeWithText("수정하기")
            .performClick()

        // then
        composeRule.waitForIdle()
        assertThat(counter).isEqualTo(1)
    }
}
