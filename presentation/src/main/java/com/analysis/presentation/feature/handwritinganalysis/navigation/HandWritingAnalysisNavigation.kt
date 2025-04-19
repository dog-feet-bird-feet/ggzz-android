package com.analysis.presentation.feature.handwritinganalysis.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.analysis.presentation.feature.handwritinganalysis.HandWritingAnalysisScreen
import com.analysis.presentation.navigation.NavRoute

internal fun NavGraphBuilder.handWritingAnalysisNavGraph() {
    composable<NavRoute.HandWritingAnalysis> {
        HandWritingAnalysisScreen()
    }
}

internal fun NavController.navigateToHandWritingAnalysis(navigateOptions: NavOptions) {
    this.navigate(NavRoute.HandWritingAnalysis, navigateOptions)
}
