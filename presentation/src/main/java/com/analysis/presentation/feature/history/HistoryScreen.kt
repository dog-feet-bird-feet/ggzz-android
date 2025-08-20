package com.analysis.presentation.feature.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.analysis.domain.model.History
import com.analysis.presentation.R
import com.analysis.presentation.component.GgzzTopAppBar
import com.analysis.presentation.feature.history.component.HistoryItemCard
import com.analysis.presentation.theme.Gray100
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun HistoryScreen(
    showErrorSnackBar: (Throwable) -> Unit,
    defaultPadding: PaddingValues,
    navigateToResult: (String) -> Unit,
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.error.collectLatest { showErrorSnackBar(it) }

        viewModel.isModifySuccess.collectLatest {
            if (it) viewModel.fetchHistories()
        }

        viewModel.isRemoveSuccess.collectLatest {
            if (it) viewModel.fetchHistories()
        }
    }

    val histories by viewModel.histories.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray100)
            .systemBarsPadding()
            .padding(defaultPadding),
    ) {
        GgzzTopAppBar(
            title = stringResource(R.string.history_top_app_bar_title),
        )
        HistoryList(
            histories,
            navigateToResult,
            modifyHistoryTitle = { id, newTitle -> viewModel.modifyHistoryTitle(id, newTitle) },
            removeHistory = { id -> viewModel.removeHistory(id) },
        )
    }
}

@Composable
private fun HistoryList(
    histories: List<History>,
    navigateToResult: (String) -> Unit,
    modifyHistoryTitle: (String, String) -> Unit,
    removeHistory: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(
            items = histories,
            key = { it.id },
        ) {
            HistoryItemCard(
                history = it,
                { id -> navigateToResult(id) },
                { id, newTitle ->
                    modifyHistoryTitle(id, newTitle)
                },
                { id -> removeHistory(id) },
            )
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview
@Composable
private fun HistoryListPreview() {
    val histories = (1..10).map {
        History(
            id = it.toString(),
            title = "History 1",
            createdAt = "2023-01-01",
            verificationImgUrl = "",
        )
    }
    HistoryList(
        histories = histories,
        navigateToResult = {},
        modifyHistoryTitle = { _, _ -> },
        removeHistory = {},
    )
}
