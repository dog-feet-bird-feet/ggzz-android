package com.analysis.presentation.feature.history.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.analysis.presentation.feature.history.HistoryScreen
import com.analysis.presentation.navigation.NavRoute

internal fun NavGraphBuilder.historyNavGraph(
    showErrorSnackbar: (Throwable) -> Unit,
    defaultPadding: PaddingValues,
    navigateToResult: (String) -> Unit,
) {
    composable<NavRoute.History> {
        HistoryScreen(showErrorSnackbar, defaultPadding, navigateToResult)
    }
}

internal fun NavController.navigateToHistory(navigateOptions: NavOptions) {
    this.navigate(NavRoute.History, navigateOptions)
}
