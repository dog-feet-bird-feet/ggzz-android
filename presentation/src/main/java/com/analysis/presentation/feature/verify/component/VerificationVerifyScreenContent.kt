package com.analysis.presentation.feature.verify.component

import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.analysis.presentation.R
import com.analysis.presentation.component.HandWritingImageItemCard
import com.analysis.presentation.component.PhotoPickerCard
import com.analysis.presentation.feature.verify.VerifyViewModel
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Gray500
import com.analysis.presentation.theme.Purple500
import com.analysis.presentation.theme.Purple700
import com.analysis.presentation.theme.White

@Composable
internal fun VerificationVerifyScreenContent(
    innerPadding: PaddingValues,
    viewModel: VerifyViewModel,
    onClickPreviousButton: () -> Unit,
    onClickAnalysisButton: () -> Unit,
    selectedVerificationUri: Uri,
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
                    targetState = selectedVerificationUri,
                    label = "",
                ) { uri ->
                    if (uri == Uri.EMPTY) {
                        PhotoPickerCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 25.dp)
                                .height(160.dp),
                            onPickPhoto = { viewModel.updatePickedVerificationUri(it) },
                        )
                    } else {
                        HandWritingImageItemCard(
                            uri = uri,
                            onClickCancelButton = { viewModel.removeVerificationUri() },
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            Button(
                modifier = Modifier
                    .height(55.dp)
                    .weight(1f),
                onClick = onClickPreviousButton,
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors().copy(containerColor = Purple700),
            ) {
                Text(
                    text = stringResource(R.string.previous_comment),
                    style = GgzzTheme.typography.pretendardSemiBold14.copy(color = White),
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(
                modifier = Modifier
                    .height(55.dp)
                    .weight(1f),
                onClick = onClickAnalysisButton,
                enabled = selectedVerificationUri != Uri.EMPTY,
                shape = RoundedCornerShape(5.dp),
                colors = ButtonColors(
                    containerColor = Purple700,
                    contentColor = White,
                    disabledContentColor = White,
                    disabledContainerColor = Gray500,
                ),
            ) {
                Text(
                    text = stringResource(R.string.analysis_comment),
                    style = GgzzTheme.typography.pretendardSemiBold14.copy(color = White),
                )
            }
        }
    }
}

@Composable
private fun GuideComment() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        StepCard(
            modifier = Modifier
                .width(70.dp)
                .height(30.dp),
            text = stringResource(R.string.verify_verification_badge_message),
            style = GgzzTheme.typography.pretendardSemiBold14.copy(
                letterSpacing = 0.5.sp,
            ),
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = stringResource(R.string.verify_verification_guide_title),
            style = GgzzTheme.typography.pretendardBold18,
        )
    }

    Spacer(modifier = Modifier.height(42.dp))

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.verify_verification_guide_comment_photo_upload),
            style = GgzzTheme.typography.pretendardRegular14,
            textAlign = TextAlign.Center,
        )
    }
}
