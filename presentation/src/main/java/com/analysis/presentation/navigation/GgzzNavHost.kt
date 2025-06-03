package com.analysis.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.analysis.presentation.feature.history.navigation.historyNavGraph
import com.analysis.presentation.feature.historydetail.navigation.historyDetailNavGraph
import com.analysis.presentation.feature.home.navigation.homeNavGraph
import com.analysis.presentation.feature.login.navigation.loginNavGraph
import com.analysis.presentation.feature.setting.navigation.settingNavGraph
import com.analysis.presentation.feature.splash.navigation.splashNavGraph
import com.analysis.presentation.feature.verify.navigation.comparisonVerifyNavGraph
import com.analysis.presentation.personality.navigation.personalityNavGraph

@Composable
internal fun GgzzNavHost(
    navController: GgzzNavController,
    startDestination: NavRoute,
    isPreWorkEnd: Boolean?,
    onStartEvent: () -> Unit,
    onSplashEndEvent: () -> Unit,
    showErrorSnackbar: (Throwable) -> Unit,
) {
    NavHost(
        navController = navController.navController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {
        splashNavGraph(
            isPreWorkEnd = isPreWorkEnd,
            onStartEvent = onStartEvent,
            onSplashEndEvent = onSplashEndEvent,
        )
        loginNavGraph(
            navigateToHome = { navController.navigateToHome() },
        )
        homeNavGraph(
            navigateToAnalysis = { navController.navigateToComparisonVerify() },
            navigateToHistory = { navController.navigateToHistory() },
            navigateToPersonality = { navController.navigateToPersonality() },
        )
        historyNavGraph(
            navigateToResult = { navController.navigateToResult(it) },
        )
        settingNavGraph()
        comparisonVerifyNavGraph(
            showErrorSnackbar = showErrorSnackbar,
            onClickNavigation = { navController.popBackStack() },
            onClickHomeButton = { navController.navigateToHome() },
        )
        historyDetailNavGraph(
            showErrorSnackbar = showErrorSnackbar,
            onClickNavigation = { navController.popBackStack() },
        )

        personalityNavGraph(
            showErrorSnackbar = showErrorSnackbar,
            onClickNavigation = { navController.popBackStack() },
            navigateToHome = { navController.navigateToHome() },
        )
    }
}
