package com.analysis.presentation.feature.historydetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.analysis.domain.model.History
import com.analysis.domain.model.VerificationResult
import com.analysis.presentation.R
import com.analysis.presentation.component.GgzzTopAppBar
import com.analysis.presentation.feature.history.component.HistoryItemCard
import com.analysis.presentation.feature.historydetail.component.VerificationResultDetailItemCard
import com.analysis.presentation.feature.historydetail.model.VerificationResultUiModel
import com.analysis.presentation.feature.historydetail.model.toVerificationResultUiModel
import com.analysis.presentation.theme.Black
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Gray100
import com.analysis.presentation.theme.Gray900
import com.analysis.presentation.theme.White
import com.analysis.presentation.util.toFormattedString
import java.time.LocalDateTime

@Composable
fun HistoryDetailScreen(
    historyId: String,
    showErrorSnackBar: (Throwable) -> Unit,
    onClickNavigation: () -> Unit,
) {

    val uiModel = VerificationResult(
        id = "1",
        title = "A필기체 검증 기록",
        createdAt = LocalDateTime.now(),
        similarity = 71.1f,
        pressure = 51.1f,
        inclination = 21.1f,
        verificationImgUrl = "https://images.unsplash.com/photo-1742240867115" +
                "-7a2f22a5b93b?q=80&w=3270&auto=format&fit=crop&ixlib=rb-4.0.3&ixi" +
                "d=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    ).toVerificationResultUiModel()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            GgzzTopAppBar(
                title = uiModel.title,
                textStyle = GgzzTheme.typography.pretendardRegular18.copy(color = Gray900),
                navigationIcon = {
                    IconButton(onClick = onClickNavigation) {
                        Image(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null,
                        )
                    }
                },
                actions = {
                    Text(
                        modifier = Modifier.padding(end = 20.dp),
                        text = uiModel.createdAt.toFormattedString(),
                        style = GgzzTheme.typography.pretendardRegular12.copy(color = Black),
                    )
                }
            )
        },
        containerColor = Gray100,
    ) { innerPadding ->
        HistoryDetailScreenContent(
            uiModel = uiModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun HistoryDetailScreenContent(
    uiModel: VerificationResultUiModel,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        shape = RoundedCornerShape(8.dp),
        color = White,
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(uiModel.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .padding(horizontal = 25.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )

            Spacer(modifier = Modifier.height(40.dp))

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
            ) {
                items(
                    items = uiModel.indicators,
                ) {
                    VerificationResultDetailItemCard(
                        verificationIndicator = it,
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HistoryDetailScreenContentPreview(modifier: Modifier = Modifier) {
    val uiModel = VerificationResult(
        id = "1",
        title = "A필기체 검증 기록",
        createdAt = LocalDateTime.now(),
        similarity = 71.1f,
        pressure = 51.1f,
        inclination = 21.1f,
        verificationImgUrl = "https://images.unsplash.com/photo-1742240867115" +
                "-7a2f22a5b93b?q=80&w=3270&auto=format&fit=crop&ixlib=rb-4.0.3&ixi" +
                "d=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    ).toVerificationResultUiModel()


    HistoryDetailScreenContent(uiModel)
}
