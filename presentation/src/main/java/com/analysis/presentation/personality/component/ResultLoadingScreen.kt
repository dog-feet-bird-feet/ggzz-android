package com.analysis.presentation.personality.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.White

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
fun ResultScreenLoadingPreview() {
    ResultLoadingScreen(
        innerPadding = PaddingValues(10.dp)
    )
}
