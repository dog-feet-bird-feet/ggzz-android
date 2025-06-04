package com.analysis.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.analysis.presentation.feature.history.navigation.historyNavGraph
import com.analysis.presentation.feature.historydetail.navigation.historyDetailNavGraph
import com.analysis.presentation.feature.home.navigation.homeNavGraph
import com.analysis.presentation.feature.login.navigation.loginNavGraph
import com.analysis.presentation.feature.setting.navigation.settingNavGraph
import com.analysis.presentation.feature.splash.navigation.splashNavGraph
import com.analysis.presentation.feature.verify.navigation.verifyNavGraph
import com.analysis.presentation.personality.navigation.personalityNavGraph

@Composable
internal fun GgzzNavHost(
    defaultPadding: PaddingValues,
    navController: GgzzNavController,
    startDestination: NavRoute,
    isPreWorkEnd: Boolean?,
    onStartEvent: () -> Unit,
    onSplashEndEvent: () -> Unit,
    showErrorSnackBar: (Throwable) -> Unit,
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
            preWork = onStartEvent,
            onSplashEndEvent = onSplashEndEvent,
        )
        loginNavGraph(
            showErrorSnackbar = showErrorSnackBar,
            navigateToHome = { navController.navigateToHome() },
        )
        homeNavGraph(
            defaultPadding = defaultPadding,
            navigateToAnalysis = { navController.navigateToComparisonVerify() },
            navigateToHistory = { navController.navigateToHistory() },
            navigateToPersonality = { navController.navigateToPersonality() },
        )
        historyNavGraph(
            showErrorSnackbar = showErrorSnackBar,
            defaultPadding = defaultPadding,
            navigateToResult = { navController.navigateToResult(it) },
        )
        settingNavGraph(defaultPadding = defaultPadding)
        verifyNavGraph(
            showErrorSnackbar = showErrorSnackBar,
            onClickNavigation = { navController.popBackStack() },
            onClickHomeButton = { navController.navigateToHome() },
        )
        historyDetailNavGraph(
            showErrorSnackbar = showErrorSnackBar,
            onClickNavigation = { navController.popBackStack() },
        )

        personalityNavGraph(
            showErrorSnackbar = showErrorSnackBar,
            onClickNavigation = { navController.popBackStack() },
            navigateToHome = { navController.navigateToHome() },
        )
    }
}
