package com.analysis.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.analysis.presentation.feature.history.navigation.historyNavGraph
import com.analysis.presentation.feature.historydetail.navigation.historyDetailNavGraph
import com.analysis.presentation.feature.home.navigation.homeNavGraph
import com.analysis.presentation.feature.setting.navigation.settingNavGraph
import com.analysis.presentation.feature.verify.navigation.comparisonVerifyNavGraph

@Composable
internal fun GgzzNavHost(
    modifier: Modifier = Modifier,
    navController: GgzzNavController,
    startDestination: NavRoute,
    showErrorSnackbar: (Throwable) -> Unit,
) {
    NavHost(
        navController = navController.navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {
        homeNavGraph(
            navigateToAnalysis = { navController.navigateToComparisonVerify() },
            navigateToHistory = { navController.navigateToHistory() },
        )
        historyNavGraph(
            navigateToResult = { navController.navigateToResult(it) },
        )
        settingNavGraph()
        comparisonVerifyNavGraph(
            showErrorSnackbar = showErrorSnackbar,
            onClickNavigation = { navController.popBackStack() },
            onClickHomeButton = {navController.navigateToHome()}
        )
        historyDetailNavGraph(
            showErrorSnackbar = showErrorSnackbar,
            onClickNavigation = { navController.popBackStack() },
        )
    }
}
