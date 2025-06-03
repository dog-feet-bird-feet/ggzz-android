package com.analysis.presentation.feature.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.analysis.presentation.feature.splash.SplashScreen
import com.analysis.presentation.navigation.NavRoute

internal fun NavGraphBuilder.splashNavGraph(
    isPreWorkEnd: Boolean?,
    preWork: () -> Unit,
    onSplashEndEvent: () -> Unit,
) {
    composable<NavRoute.Splash> {
        SplashScreen(
            isPreWorkEnd = isPreWorkEnd,
            preWork = preWork,
            onSplashEndEvent = onSplashEndEvent,
        )
    }
}
