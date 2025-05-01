package com.analysis.presentation.feature.historydetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.analysis.domain.model.History
import com.analysis.presentation.R
import com.analysis.presentation.component.GgzzTopAppBar
import com.analysis.presentation.theme.Black
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Gray100
import com.analysis.presentation.theme.Gray900
import com.analysis.presentation.util.toFormattedString
import java.time.LocalDateTime

@Composable
fun HistoryDetailScreen(
    historyId: String,
    showErrorSnackBar: (Throwable) -> Unit,
    onClickNavigation: () -> Unit,
) {
    val history = History(
        id = "1",
        name = "A필기체 검증 기록",
        createdAt = LocalDateTime.now(),
        verificationImgUrl = "https://images.unsplash.com/photo-1742240867115" +
                "-7a2f22a5b93b?q=80&w=3270&auto=format&fit=crop&ixlib=rb-4.0.3&ixi" +
                "d=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            GgzzTopAppBar(
                title = history.name,
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
                        text = history.createdAt.toFormattedString(),
                        style = GgzzTheme.typography.pretendardRegular12.copy(color = Black),
                    )
                }
            )
        },
        containerColor = Gray100,
    ) { innerPadding ->

    }
}
