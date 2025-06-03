package com.analysis.presentation.personality.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.analysis.domain.model.Personality
import com.analysis.domain.model.TraitDetail
import com.analysis.domain.model.Traits
import com.analysis.presentation.R
import com.analysis.presentation.personality.model.PersonalityUiState
import com.analysis.presentation.personality.model.toPersonalityUiState
import com.analysis.presentation.theme.Blue300
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Purple700
import com.analysis.presentation.theme.White

@Composable
internal fun ResultScreen(
    innerPadding: PaddingValues,
    uiState: PersonalityUiState.Analyzing,
    onClickHomeButton: () -> Unit,
) {
    when (uiState) {
        PersonalityUiState.Analyzing.Loading -> ResultLoadingScreen(innerPadding)
        is PersonalityUiState.Analyzing.Success -> ResultScreenContent(
            innerPadding,
            uiState.personality,
            onClickHomeButton,
        )
    }
}


@Composable
internal fun ResultScreenContent(
    innerPadding: PaddingValues,
    personality: Personality,
    onClickHomeButton: () -> Unit,
) {
    val scrollState = rememberScrollState()

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
                    .verticalScroll(state = scrollState)
                    .padding(horizontal = 20.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        modifier = Modifier.size(200.dp),
                        painter = painterResource(R.drawable.personality_analyzing_complete),
                        contentDescription = null,
                    )

                    Spacer(modifier = Modifier.height(13.dp))

                    Text(
                        text = personality.type,
                        style = GgzzTheme.typography.pretendardSemiBold26.copy(color = Blue300),
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = personality.typeDescription,
                        style = GgzzTheme.typography.pretendardRegular16.copy(color = Blue300),
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_text_size),
                        contentDescription = null,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = stringResource(
                            R.string.personlity_handwriting_size,
                            personality.traits.size.score,
                        ),
                        style = GgzzTheme.typography.pretendardSemiBold18,
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = personality.traits.size.detail,
                    style = GgzzTheme.typography.pretendardRegular14,
                )

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_pressure),
                        contentDescription = null,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = stringResource(
                            R.string.personlity_handwriting_pressure,
                            personality.traits.pressure.score,
                        ),
                        style = GgzzTheme.typography.pretendardSemiBold18,
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = personality.traits.pressure.detail,
                    style = GgzzTheme.typography.pretendardRegular14,
                )

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_inclination),
                        contentDescription = null,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = stringResource(
                            R.string.personlity_handwriting_inclination,
                            personality.traits.inclination.score,
                        ),
                        style = GgzzTheme.typography.pretendardSemiBold18,
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = personality.traits.inclination.detail,
                    style = GgzzTheme.typography.pretendardRegular14,
                )

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_shape),
                        contentDescription = null,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = stringResource(
                            R.string.personlity_handwriting_shape,
                            personality.traits.shape.score,
                        ),
                        style = GgzzTheme.typography.pretendardSemiBold18,
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = personality.traits.shape.detail,
                    style = GgzzTheme.typography.pretendardRegular14,
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = stringResource(R.string.personality_summary),
                    style = GgzzTheme.typography.pretendardSemiBold18,
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = personality.description,
                    style = GgzzTheme.typography.pretendardRegular14,
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

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
fun ResultLoadingScreen(innerPadding: PaddingValues) {
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
                    text = "필체 분석 중",
                    style = GgzzTheme.typography.pretendardBold16,
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "업로드한 필체를 분석하고 있어요.\n 조금만 기다려주세요!",
                    style = GgzzTheme.typography.pretendardRegular14,
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ResultScreenPreview() {
    val uiState = Personality(
        id = 1,
        traits = Traits(
            size = TraitDetail(score = "작음", detail = "내향적, 치밀함, 절약 정신, 조심스러움"),
            pressure = TraitDetail(score = "약함", detail = "유순함, 수줍음, 에너지가 약함, 민감함"),
            inclination = TraitDetail(
                score = "좌측",
                detail = "비관적, 감정표현을 잘 안함, 차가움, 비판적",
            ),
            shape = TraitDetail(
                score = "둥글",
                detail = "사고가 유연함, 상상력이 풍부, 원만함, 합리적임",
            ),
        ),
        type = "섬세한 관찰자",
        typeDescription = "내향적이고 유순하며 비판적이지만 유연한 사고",
        description = "내향적이고 치밀하며 조심스러운 성향을 지녔고 유순하고 민감하며 에너지는 다소 낮지만" +
                " 주변에 조화롭게 어울립니다. 감정을 드러내는 데 소극적이고 신중하며 다소 비판적인 관점을 지닙니다." +
                " 사고가 유연하고 상상력이 풍부하여 원만하고 합리적인 관계를 지향하는 경향이 있습니다.",
    ).toPersonalityUiState()

    ResultScreen(
        innerPadding = PaddingValues(10.dp),
        uiState,
        {},
    )
}
