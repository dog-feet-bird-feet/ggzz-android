package com.analysis.presentation.component

import androidx.compose.material3.IconButton
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GgzzTopAppBarTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `AppBar의 title이 화면에 보인다`() {
        // given
        composeRule.setContent {
            GgzzTopAppBar(
                title = "끄적",
            )
        }

        // then
        composeRule.onNodeWithText("끄적")
            .assertIsDisplayed()
    }

    @Test
    fun `AppBar의 navigation 아이콘이 보인다`() {
        // given
        composeRule.setContent {
            GgzzTopAppBar(
                title = "끄적",
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.testTag("navigationIcon"),
                        onClick = { },
                    ) {}
                },
            )
        }

        // then
        composeRule.onNodeWithTag("navigationIcon")
            .assertIsDisplayed()
    }

    @Test
    fun `AppBar의 actions 아이콘들이 보인다`() {
        // given
        composeRule.setContent {
            GgzzTopAppBar(
                title = "끄적",
                actions = {
                    IconButton(
                        modifier = Modifier.testTag("actions1"),
                        onClick = {},
                    ) {}

                    IconButton(
                        modifier = Modifier.testTag("actions2"),
                        onClick = {},
                    ) {}
                },
            )
        }

        // then
        composeRule.onNodeWithTag("actions1")
            .assertIsDisplayed()

        composeRule.onNodeWithTag("actions2")
            .assertIsDisplayed()
    }
}
