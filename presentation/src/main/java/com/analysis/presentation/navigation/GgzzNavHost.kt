package com.analysis.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.analysis.presentation.feature.handwritinganalysis.navigation.handWritingAnalysisNavGraph
import com.analysis.presentation.feature.history.navigation.historyNavGraph
import com.analysis.presentation.feature.home.navigation.homeNavGraph
import com.analysis.presentation.feature.setting.navigation.settingNavGraph

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
    ) {
        homeNavGraph(
            navigateToAnalysis = {navController.navigateToHandWritingAnalysis()},
            navigateToHistory = { navController.navigateToHistory() }
        )
        historyNavGraph()
        settingNavGraph()
        handWritingAnalysisNavGraph()
    }
}
