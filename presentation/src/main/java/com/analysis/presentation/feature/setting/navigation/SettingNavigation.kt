package com.analysis.presentation.feature.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.analysis.presentation.feature.setting.SettingScreen
import com.analysis.presentation.navigation.NavRoute

internal fun NavGraphBuilder.settingNavGraph() {
    composable<NavRoute.Setting> {
        SettingScreen()
    }
}

internal fun NavController.navigateToSetting(navigateOptions: NavOptions) {
    this.navigate(NavRoute.Setting, navigateOptions)
}
