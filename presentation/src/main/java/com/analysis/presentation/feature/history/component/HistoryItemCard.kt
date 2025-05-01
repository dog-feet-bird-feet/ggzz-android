package com.analysis.presentation.feature.history.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.analysis.domain.model.History
import com.analysis.presentation.component.GgzzDropMenuButton
import com.analysis.presentation.model.DropMenuItem
import com.analysis.presentation.theme.Black
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.White
import com.analysis.presentation.util.toFormattedString
import java.time.LocalDateTime

@Composable
fun HistoryItemCard(
    history: History,
    onHistoryClick: (String) -> Unit,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = { onHistoryClick(history.id) },
                ),
        colors = CardDefaults.cardColors().copy(containerColor = White),
        shape = RoundedCornerShape(4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f),
            ) {
                Text(
                    text = history.name,
                    style = GgzzTheme.typography.pretendardSemiBold14.copy(color = Black),
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = history.createdAt.toFormattedString(),
                    style = GgzzTheme.typography.pretendardRegular12.copy(color = Black),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(history.verificationImgUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(90.dp)
                        .height(73.dp)
                        .clip(RoundedCornerShape(4.dp)),
                )

                Spacer(modifier = Modifier.width(2.dp))

                GgzzDropMenuButton(
                    listOf(
                        DropMenuItem("수정하기", {}),
                        DropMenuItem("삭제하기", {}),
                    ),
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HistoryItemCardPreview() {
    val history = History(
        id = "01JRSYFFCD6R6C88JJFA0JTZPB",
        name = "테스트 결과",
        createdAt = LocalDateTime.now(),
        verificationImgUrl = "https://images.unsplash.com/photo-1742240867115-7a2f22a5b93b?" +
            "q=80&w=3270&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlf" +
            "Hx8fGVufDB8fHx8fA%3D%3D",
    )

    Column {
        HistoryItemCard(
            history = history,
            onHistoryClick = {},
        )
    }
}
