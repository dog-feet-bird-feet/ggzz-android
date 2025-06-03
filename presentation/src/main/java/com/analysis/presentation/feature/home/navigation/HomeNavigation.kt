package com.analysis.presentation.feature.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.analysis.presentation.feature.home.HomeScreen
import com.analysis.presentation.navigation.NavRoute

internal fun NavGraphBuilder.homeNavGraph(
    defaultPadding:PaddingValues,
    navigateToAnalysis: () -> Unit,
    navigateToHistory: () -> Unit,
    navigateToPersonality: () -> Unit,
) {
    composable<NavRoute.Home> {
        HomeScreen(defaultPadding,navigateToAnalysis, navigateToHistory, navigateToPersonality)
    }
}

internal fun NavController.navigateToHome(navigateOptions: NavOptions) {
    this.navigate(NavRoute.Home, navigateOptions)
}
