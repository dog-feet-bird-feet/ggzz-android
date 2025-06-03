package com.analysis.presentation.feature.verify.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.analysis.domain.model.AnalysisResult
import com.analysis.presentation.R
import com.analysis.presentation.component.ResultDetailItemCard
import com.analysis.presentation.feature.verify.model.VerificationUiState
import com.analysis.presentation.feature.verify.model.toVerificationResultUiState
import com.analysis.presentation.theme.Blue300
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Purple700
import com.analysis.presentation.theme.Red500
import com.analysis.presentation.theme.White
import com.analysis.presentation.util.modifier.dropShadow

@Composable
internal fun ResultScreen(
    innerPadding: PaddingValues,
    uiState: VerificationUiState.Verification,
    onClickHomeButton: () -> Unit,
) {
    when (uiState) {
        VerificationUiState.Verification.Loading -> ResultScreenLoading(innerPadding)
        is VerificationUiState.Verification.Result -> ResultScreenContent(
            innerPadding,
            uiState,
            onClickHomeButton,
        )
    }
}

@Composable
fun ResultScreenContent(
    innerPadding: PaddingValues,
    result: VerificationUiState.Verification.Result,
    onClickHomeButton: () -> Unit,
) {
    val isSimilar = result.indicators[0].percentage >= 50.0

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(top = 20.dp)
            .padding(horizontal = 20.dp),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
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
                Spacer(modifier = Modifier.height(32.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .dropShadow(
                            shape = RoundedCornerShape(15.dp),
                            offsetX = 2.dp,
                            offsetY = 2.dp,
                            blur = 10.dp,
                            color = Color.Black.copy(0.05f),
                        ),
                    shape = RoundedCornerShape(15.dp),
                    colors = CardDefaults.cardColors(containerColor = White),
                ) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        if (isSimilar) {
                            Image(
                                painter = painterResource(R.drawable.ic_similar),
                                contentDescription = null,
                            )
                        } else {
                            Image(
                                painter = painterResource(R.drawable.ic_not_similar),
                                contentDescription = null,
                            )
                        }

                        Spacer(modifier = Modifier.height(13.dp))

                        Text(
                            text = "감정 완료",
                            style = GgzzTheme.typography.pretendardBold28.copy(color = Blue300),
                        )

                        Spacer(modifier = Modifier.height(13.dp))

                        SimilarityResultText(isSimilar)
                    }

                    Spacer(modifier = Modifier.height(13.dp))
                }

                Spacer(modifier = Modifier.height(30.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(30.dp),
                    contentPadding = PaddingValues(bottom = 40.dp),
                ) {
                    items(result.indicators) {
                        ResultDetailItemCard(resultIndicator = it)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onClickHomeButton,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Purple700),
        ) {
            Text(
                text = stringResource(R.string.verify_move_home_comment),
                style = GgzzTheme.typography.pretendardSemiBold14.copy(color = White),
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun SimilarityResultText(isSimilar: Boolean) {
    Text(
        text = buildAnnotatedString {
            val highlightText = if (isSimilar) "유사한 필적" else "유사하지 않은 필적"
            val highlightColor = if (isSimilar) Blue300 else Red500
            val semiBold30 = GgzzTheme.typography.pretendardSemiBold23

            withStyle(
                style = SpanStyle(
                    color = highlightColor,
                    fontSize = semiBold30.fontSize,
                    fontWeight = semiBold30.fontWeight,
                ),
            ) {
                append(highlightText)
            }
            append("입니다")
        },
        style = GgzzTheme.typography.pretendardRegular24.copy(color = Blue300),
    )
}

@Composable
fun ResultScreenLoading(innerPadding: PaddingValues) {
    Surface(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 20.dp)
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(8.dp),
        color = White,
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp),
        ) {
            GuideComment()

            Spacer(modifier = Modifier.height(70.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(112.dp),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    color = GgzzTheme.colorScheme.primary,
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "필적 감정 중",
                    style = GgzzTheme.typography.pretendardBold16,
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "업로드한 필적을 비교하고 있어요.\n 조금만 기다려주세요!",
                    style = GgzzTheme.typography.pretendardRegular14,
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(modifier = Modifier.height(100.dp))
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
            text = "step3",
            style = GgzzTheme.typography.pretendardSemiBold14.copy(
                letterSpacing = 0.5.sp,
            ),
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "감정 결과 확인",
            style = GgzzTheme.typography.pretendardBold18,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ResultScreenContentPreview() {
    val uiModel = AnalysisResult(
        similarity = 0.4f,
        pressure = 0.5f,
        inclination = 0.2f,
    ).toVerificationResultUiState()

    ResultScreenContent(
        innerPadding = PaddingValues(10.dp),
        uiModel as VerificationUiState.Verification.Result,
        {},
    )
}

@Composable
@Preview(showBackground = true)
fun ResultScreenLoadingPreview() {
    ResultScreenLoading(innerPadding = PaddingValues(10.dp))
}
