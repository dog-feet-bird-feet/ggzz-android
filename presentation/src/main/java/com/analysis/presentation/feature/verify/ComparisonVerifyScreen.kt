package com.analysis.presentation.feature.verify

import android.net.Uri
import androidx.compose.foundation.Image
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.analysis.presentation.R
import com.analysis.presentation.component.GgzzTopAppBar
import com.analysis.presentation.feature.verify.component.PickedPhotoList
import com.analysis.presentation.feature.verify.component.StepCard
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Gray100
import com.analysis.presentation.theme.Gray500
import com.analysis.presentation.theme.Gray900
import com.analysis.presentation.theme.Purple500
import com.analysis.presentation.theme.Purple700
import com.analysis.presentation.theme.White

@Composable
internal fun ComparisonVerifyScreen(
    onClickNavigation: () -> Unit,
    viewModel: VerifyViewModel = hiltViewModel(),
) {
    val selectedComparisonUris by viewModel.selectedComparisonUris.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            GgzzTopAppBar(
                title = "필적 감정하기",
                textStyle = GgzzTheme.typography.pretendardRegular18.copy(color = Gray900),
                navigationIcon = {
                    IconButton(onClick = onClickNavigation) {
                        Image(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null,
                        )
                    }
                }
            )
        },
        containerColor = Gray100
    ) { innerPadding ->
        ComparisonVerifyScreenContent(innerPadding, selectedComparisonUris, viewModel)
    }
}

@Composable
private fun ComparisonVerifyScreenContent(
    innerPadding: PaddingValues,
    selectedComparisonUris: List<Uri>,
    viewModel: VerifyViewModel,
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(20.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(630.dp),
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
                    removeComparisonUri = { viewModel.removeComparisonUri(it) }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            onClick = {},
            enabled = canGoVerificationVerifyScreen(selectedComparisonUris),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonColors(
                containerColor = Purple700,
                contentColor = White,
                disabledContentColor = White,
                disabledContainerColor = Gray500,
            )
        ) {
            Text(
                text = "다음",
                style = GgzzTheme.typography.pretendardSemiBold14.copy(color = White)
            )
        }
    }
}


private fun canGoVerificationVerifyScreen(selectedComparisonUris: List<Uri>): Boolean =
    (selectedComparisonUris.size in 1..5)

@Composable
private fun GuideComment() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        StepCard(
            modifier = Modifier
                .width(70.dp)
                .height(30.dp),
            text = "STEP 1",
            style = GgzzTheme.typography.pretendardSemiBold14.copy(
                letterSpacing = 0.5.sp
            )
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "대조할 필적 이미지 업로드",
            style = GgzzTheme.typography.pretendardBold18
        )
    }

    Spacer(modifier = Modifier.height(10.dp))

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "필적 분석을 위해 최대 5장의 필체 사진이 필요해요!",
            style = GgzzTheme.typography.pretendardRegular14
        )
        Text(
            text = "* 한 장에 최대 2MB 까지 업로드 가능합니다.",
            style = GgzzTheme.typography.pretendardRegular14.copy(color = Purple500)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ComparisonVerifyScreenPreview() {
    ComparisonVerifyScreen({})
}
