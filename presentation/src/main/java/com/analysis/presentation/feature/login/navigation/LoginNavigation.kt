package com.analysis.presentation.feature.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.analysis.presentation.feature.login.LoginScreen
import com.analysis.presentation.navigation.NavRoute

internal fun NavGraphBuilder.loginNavGraph(
    showErrorSnackBar: (Throwable) -> Unit,
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
) {
    composable<NavRoute.Login> {
        LoginScreen(showErrorSnackBar, navigateToHome, navigateToSignUp)
    }
}

internal fun NavController.navigateToLogin(navigateOptions: NavOptions) {
    this.navigate(NavRoute.Login, navigateOptions)
}
