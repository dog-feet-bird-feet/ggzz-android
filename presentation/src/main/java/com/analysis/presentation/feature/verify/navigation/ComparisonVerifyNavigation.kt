package com.analysis.presentation.feature.verify.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.analysis.presentation.feature.verify.ComparisonVerifyScreen
import com.analysis.presentation.navigation.NavRoute

internal fun NavGraphBuilder.comparisonVerifyNavGraph(
    onClickNavigation: () -> Unit,
) {
    composable<NavRoute.ComparisonVerify> {
        ComparisonVerifyScreen(onClickNavigation)
    }
}

internal fun NavController.navigateToComparisonVerify(navigateOptions: NavOptions) {
    this.navigate(NavRoute.ComparisonVerify, navigateOptions)
}
