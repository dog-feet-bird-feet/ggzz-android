package com.analysis.presentation.feature.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.analysis.domain.model.HistoryItem
import com.analysis.presentation.R
import com.analysis.presentation.component.GgzzTopAppBar
import com.analysis.presentation.feature.history.component.HistoryItemCard
import com.analysis.presentation.theme.Gray100
import java.time.LocalDateTime

@Composable
internal fun HistoryScreen() {
    val historyItems = (1..20).map {
        HistoryItem(
            id = it.toString(),
            name = "테스트 결과",
            createdAt = LocalDateTime.now(),
            verificationImgUrl = "https://images.unsplash.com/photo-1742240867115" +
                "-7a2f22a5b93b?q=80&w=3270&auto=format&fit=crop&ixlib=rb-4.0.3&ixi" +
                "d=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        )
    }

    Scaffold(
        topBar = {
            GgzzTopAppBar(
                title = stringResource(R.string.home_top_app_bar_title),
            )
        },
        containerColor = Gray100,
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                items = historyItems,
                key = { it.id },
            ) {
                HistoryItemCard(
                    historyItem = it,
                    {},
                )
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
