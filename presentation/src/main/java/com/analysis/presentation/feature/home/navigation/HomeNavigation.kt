package com.analysis.presentation.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.analysis.presentation.feature.home.HomeScreen
import com.analysis.presentation.navigation.NavRoute

internal fun NavGraphBuilder.homeNavGraph(
    navigateToAnalysis: () -> Unit,
    navigateToHistory: () -> Unit,
) {
    composable<NavRoute.Home> {
        HomeScreen(navigateToAnalysis, navigateToHistory)
    }
}

internal fun NavController.navigateToHome(navigateOptions: NavOptions) {
    this.navigate(NavRoute.Home, navigateOptions)
}
