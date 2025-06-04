package com.analysis.presentation.component

import android.net.Uri
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HandWritingImageItemCardTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `x버튼을 누르면 onClickCancelButton이 호출된다`() {
        // given
        val fakeUri = Uri.parse("content://test/image.png")
        var clickedUri: Uri? = null

        composeRule.setContent {
            HandWritingImageItemCard(
                uri = fakeUri,
                onClickCancelButton = { uri ->
                    clickedUri = uri
                }
            )
        }

        // when
        composeRule.onNodeWithTag("cancelButton")
            .performClick()

        // then
        composeRule.waitForIdle()
        assertThat(clickedUri).isEqualTo(fakeUri)
    }
}
