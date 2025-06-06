package com.analysis.presentation.feature.personality.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.analysis.presentation.feature.personality.PersonalityScreen
import com.analysis.presentation.navigation.NavRoute

internal fun NavGraphBuilder.personalityNavGraph(
    showErrorSnackbar: (Throwable) -> Unit,
    onClickNavigation: () -> Unit,
    navigateToHome: () -> Unit,
) {
    composable<NavRoute.Personality> {
        PersonalityScreen(showErrorSnackbar, onClickNavigation, navigateToHome)
    }
}

internal fun NavController.navigateToPersonality(navigateOptions: NavOptions) {
    this.navigate(NavRoute.Personality, navigateOptions)
}
