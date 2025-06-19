package com.analysis.presentation.feature.verify.component

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.analysis.presentation.R
import com.analysis.presentation.feature.verify.VerifyViewModel
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Gray500
import com.analysis.presentation.theme.Purple500
import com.analysis.presentation.theme.Purple700
import com.analysis.presentation.theme.White

@Composable
internal fun ComparisonVerifyScreenContent(
    innerPadding: PaddingValues,
    selectedComparisonUris: List<Uri>,
    viewModel: VerifyViewModel,
    onClickNextButton: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(20.dp),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(630.dp),
            shape = RoundedCornerShape(8.dp),
            color = White,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp)
                    .padding(horizontal = 20.dp),
            ) {
                GuideComment()

                Spacer(modifier = Modifier.height(40.dp))

                PickedPhotoList(
                    selectedComparisonUris = selectedComparisonUris,
                    updatePickedComparisonUris = { viewModel.updatePickedComparisonUris(it) },
                    removeComparisonUri = { viewModel.removeComparisonUri(it) },
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            onClick = onClickNextButton,
            enabled = canGoVerificationVerifyScreen(selectedComparisonUris),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonColors(
                containerColor = Purple700,
                contentColor = White,
                disabledContentColor = White,
                disabledContainerColor = Gray500,
            ),
        ) {
            Text(
                text = stringResource(R.string.next_comment),
                style = GgzzTheme.typography.pretendardSemiBold14.copy(color = White),
            )
        }
    }
}

private fun canGoVerificationVerifyScreen(selectedComparisonUris: List<Uri>): Boolean = (selectedComparisonUris.size in 1..5)

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
            text = stringResource(R.string.verify_comparison_badge_message),
            style = GgzzTheme.typography.pretendardSemiBold14.copy(
                letterSpacing = 0.5.sp,
            ),
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = stringResource(R.string.verify_comparison_guide_title),
            style = GgzzTheme.typography.pretendardBold18,
        )
    }

    Spacer(modifier = Modifier.height(10.dp))

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.verify_comparison_guide_comment_photo_count),
            style = GgzzTheme.typography.pretendardRegular14,
        )
        Text(
            text = stringResource(R.string.guide_comment_photo_format),
            style = GgzzTheme.typography.pretendardRegular14.copy(color = Purple500),
        )
    }
}
