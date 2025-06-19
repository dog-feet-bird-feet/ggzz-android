package com.analysis.presentation.feature.setting.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.analysis.presentation.feature.setting.SettingScreen
import com.analysis.presentation.navigation.NavRoute

internal fun NavGraphBuilder.settingNavGraph(
    defaultPadding: PaddingValues,
    navigateToLogin: () -> Unit,
) {
    composable<NavRoute.Setting> {
        SettingScreen(
            defaultPadding,
            navigateToLogin = navigateToLogin,
        )
    }
}

internal fun NavController.navigateToSetting(navigateOptions: NavOptions) {
    this.navigate(NavRoute.Setting, navigateOptions)
}
