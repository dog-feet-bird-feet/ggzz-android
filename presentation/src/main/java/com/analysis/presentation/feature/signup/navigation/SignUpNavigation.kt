package com.analysis.presentation.feature.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.analysis.presentation.feature.signup.SignUpScreen
import com.analysis.presentation.navigation.NavRoute

internal fun NavGraphBuilder.signUpNavGraph(
    showErrorSnackBar: (Throwable) -> Unit,
    onClickNavigation: () -> Unit,
    navigateToHome: () -> Unit,
) {
    composable<NavRoute.SignUp> {
        SignUpScreen(showErrorSnackBar, onClickNavigation, navigateToHome)
    }
}

internal fun NavController.navigateToSignUp(navigateOptions: NavOptions) {
    this.navigate(NavRoute.SignUp, navigateOptions)
}
