package com.analysis.presentation.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GgzzAlertDialogTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `다이얼로그의 제목이 화면에 표시된다`() {
        // given
        composeRule.setContent {
            GgzzAlertDialog(
                dialogTitle = "타이틀",
                text = "",
                showModifyDialog = true,
                onDismissRequest = {},
                onClickConfirm = {},
            )
        }

        // then
        composeRule.onNodeWithText("타이틀")
            .assertIsDisplayed()
    }

    @Test
    fun `현재 텍스트가 화면에 표시된다`() {
        // given
        composeRule.setContent {
            GgzzAlertDialog(
                dialogTitle = "",
                text = "텍스트",
                showModifyDialog = true,
                onDismissRequest = {},
                onClickConfirm = {},
            )
        }

        // then
        composeRule.onNodeWithText("텍스트")
            .assertIsDisplayed()
    }

    @Test
    fun `다이얼로그의 취소버튼이 화면에 표시된다`() {
        // given
        composeRule.setContent {
            GgzzAlertDialog(
                dialogTitle = "",
                text = "",
                showModifyDialog = true,
                onDismissRequest = {},
                onClickConfirm = {},
            )
        }

        // then
        composeRule.onNodeWithText("취소")
            .assertIsDisplayed()
    }

    @Test
    fun `다이얼로그의 취소버튼을 누르면 onDismissRequest가 호출된다`() {
        // given
        var counter = 0
        composeRule.setContent {
            GgzzAlertDialog(
                dialogTitle = "",
                text = "",
                showModifyDialog = true,
                onDismissRequest = { counter++ },
                onClickConfirm = {},
            )
        }

        // when
        composeRule.onNodeWithText("취소")
            .performClick()

        // then
        composeRule.waitForIdle()
        assertThat(counter).isEqualTo(1)
    }

    @Test
    fun `다이얼로그의 확인버튼이 화면에 표시된다`() {
        // given
        composeRule.setContent {
            GgzzAlertDialog(
                dialogTitle = "",
                text = "",
                showModifyDialog = true,
                onDismissRequest = {},
                onClickConfirm = {},
            )
        }

        // then
        composeRule.onNodeWithText("확인")
            .assertIsDisplayed()
    }

    @Test
    fun `다이얼로그의 확인버튼을 누르면 onClickConfirm이 호출된다`() {
        // given
        var counter = 0
        composeRule.setContent {
            GgzzAlertDialog(
                dialogTitle = "",
                text = "",
                showModifyDialog = true,
                onDismissRequest = {},
                onClickConfirm = { counter++ },
            )
        }

        // when
        composeRule.onNodeWithText("확인")
            .performClick()

        // then
        composeRule.waitForIdle()
        assertThat(counter).isEqualTo(1)
    }
}
