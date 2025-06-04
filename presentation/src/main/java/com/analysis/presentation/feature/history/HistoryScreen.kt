package com.analysis.presentation.feature.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.analysis.presentation.R
import com.analysis.presentation.component.GgzzTopAppBar
import com.analysis.presentation.feature.history.component.HistoryItemCard
import com.analysis.presentation.theme.Gray100
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun HistoryScreen(
    showErrorSnackbar: (Throwable) -> Unit,
    defaultPadding: PaddingValues,
    navigateToResult: (String) -> Unit,
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.isModifySuccess.collectLatest {
            if (it) viewModel.fetchHistories()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.isRemoveSuccess.collectLatest {
            if (it) viewModel.fetchHistories()
        }
    }

    val histories by viewModel.histories.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            GgzzTopAppBar(
                title = stringResource(R.string.history_top_app_bar_title),
            )
        },
        containerColor = Gray100,
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(defaultPadding)
                .padding(innerPadding)
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
                    { navigateToResult(it) },
                    { id, newTitle ->
                        viewModel.modifyHistoryTitle(id, newTitle)
                    },
                    { viewModel.removeHistory(it) },
                )
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
