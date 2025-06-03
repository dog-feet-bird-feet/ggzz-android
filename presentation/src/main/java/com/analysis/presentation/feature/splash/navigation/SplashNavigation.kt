package com.analysis.presentation.feature.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.analysis.presentation.feature.splash.SplashScreen
import com.analysis.presentation.navigation.NavRoute

internal fun NavGraphBuilder.splashNavGraph(
    isPreWorkEnd: Boolean?,
    onStartEvent: () -> Unit,
    onSplashEndEvent: () -> Unit,
) {
    composable<NavRoute.Splash> {
        SplashScreen(
            isPreWorkEnd = isPreWorkEnd,
            onStartEvent = onStartEvent,
            onSplashEndEvent = onSplashEndEvent,
        )
    }
}
