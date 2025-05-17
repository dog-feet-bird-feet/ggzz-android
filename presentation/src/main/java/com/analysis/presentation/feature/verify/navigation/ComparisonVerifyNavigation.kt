package com.analysis.presentation.feature.verify.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.analysis.presentation.feature.verify.VerifyScreen
import com.analysis.presentation.navigation.NavRoute

internal fun NavGraphBuilder.comparisonVerifyNavGraph(
    showErrorSnackbar: (Throwable) -> Unit,
    onClickNavigation: () -> Unit,
    onClickHomeButton: () -> Unit,
) {
    composable<NavRoute.ComparisonVerify> {
        VerifyScreen(showErrorSnackbar, onClickNavigation, onClickHomeButton)
    }
}

internal fun NavController.navigateToComparisonVerify(navigateOptions: NavOptions) {
    this.navigate(NavRoute.ComparisonVerify, navigateOptions)
}
