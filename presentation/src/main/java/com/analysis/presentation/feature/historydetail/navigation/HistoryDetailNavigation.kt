package com.analysis.presentation.feature.historydetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.analysis.presentation.feature.historydetail.HistoryDetailScreen
import com.analysis.presentation.navigation.NavRoute

internal fun NavGraphBuilder.historyDetailNavGraph(
    showErrorSnackbar: (Throwable) -> Unit,
    onClickNavigation: () -> Unit,
) {
    composable<NavRoute.HistoryDetail> {
        HistoryDetailScreen(showErrorSnackbar, onClickNavigation)
    }
}

internal fun NavController.navigateToResult(
    historyId: String,
    navigateOptions: NavOptions,
) {
    this.navigate(NavRoute.HistoryDetail(historyId), navigateOptions)
}
