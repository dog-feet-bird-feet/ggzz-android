package com.analysis.presentation.personality.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.analysis.presentation.navigation.NavRoute
import com.analysis.presentation.personality.PersonalityScreen

internal fun NavGraphBuilder.personalityNavGraph(
    onClickNavigation: () -> Unit,
    navigateToHome: () -> Unit,
) {
    composable<NavRoute.Personality> {
        PersonalityScreen(onClickNavigation,navigateToHome)
    }
}

internal fun NavController.navigateToPersonality(navigateOptions: NavOptions) {
    this.navigate(NavRoute.Personality, navigateOptions)
}
