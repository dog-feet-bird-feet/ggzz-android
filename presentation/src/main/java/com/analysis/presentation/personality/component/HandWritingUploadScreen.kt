package com.analysis.presentation.personality.component

import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.analysis.presentation.R
import com.analysis.presentation.component.PhotoPickerCard
import com.analysis.presentation.feature.verify.component.HandWritingImageItemCard
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Gray500
import com.analysis.presentation.theme.Purple500
import com.analysis.presentation.theme.Purple700
import com.analysis.presentation.theme.White

@Composable
fun HandWritingUploadScreen(
    innerPadding: PaddingValues,
    showErrorSnackBar: (Throwable) -> Unit,
    onPickPhoto: (Uri) -> Unit = {},
    onClickCancelButton: (Uri) -> Unit,
    onClickAnalyzingButton: () -> Unit,
    selectedHandWritingUri: Uri? = null,
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(20.dp),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(8.dp),
            color = White,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .padding(horizontal = 20.dp),
            ) {
                GuideComment()

                Spacer(modifier = Modifier.height(45.dp))

                AnimatedContent(
                    targetState = selectedHandWritingUri,
                    label = "",
                ) { uri ->
                    if (uri == null) {
                        PhotoPickerCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 25.dp)
                                .height(160.dp),
                            maxSelectable = 1,
                            showErrorSnackBar = showErrorSnackBar,
                            onPickPhoto = { onPickPhoto(it) },
                        )
                    } else {
                        HandWritingImageItemCard(
                            uri = uri,
                            onClickCancelButton = { onClickCancelButton(it) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 25.dp)
                                .height(160.dp),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(R.string.guide_comment_photo_format),
                        style = GgzzTheme.typography.pretendardRegular14.copy(color = Purple500),
                    )
                }

                Spacer(modifier = Modifier.height(42.dp))
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            onClick = onClickAnalyzingButton,
            enabled = selectedHandWritingUri != null,
            shape = RoundedCornerShape(5.dp),
            colors = ButtonColors(
                containerColor = Purple700,
                contentColor = White,
                disabledContentColor = White,
                disabledContainerColor = Gray500,
            ),
        ) {
            Text(
                text = stringResource(R.string.analyze_comment),
                style = GgzzTheme.typography.pretendardSemiBold14.copy(color = White),
            )
        }
    }
}

@Composable
private fun GuideComment() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.personality_guide_title),
            style = GgzzTheme.typography.pretendardBold18,
        )

        Spacer(modifier = Modifier.height(42.dp))

        Text(
            text = stringResource(R.string.personality_guide_comment_photo_upload),
            style = GgzzTheme.typography.pretendardRegular14,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HandWritingUploadScreenPreview() {
    HandWritingUploadScreen(
        PaddingValues(0.dp),
        {},
        {},
        {},
        {},
    )
}
