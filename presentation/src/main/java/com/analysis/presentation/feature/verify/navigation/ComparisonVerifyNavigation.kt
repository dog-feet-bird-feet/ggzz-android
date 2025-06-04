package com.analysis.presentation.feature.verify.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.analysis.presentation.feature.verify.VerifyScreen
import com.analysis.presentation.navigation.NavRoute

internal fun NavGraphBuilder.verifyNavGraph(
    showErrorSnackbar: (Throwable) -> Unit,
    onClickNavigation: () -> Unit,
    onClickHomeButton: () -> Unit,
) {
    composable<NavRoute.Verify> {
        VerifyScreen(showErrorSnackbar, onClickNavigation, onClickHomeButton)
    }
}

internal fun NavController.navigateToVerify(navigateOptions: NavOptions) {
    this.navigate(NavRoute.Verify, navigateOptions)
}
