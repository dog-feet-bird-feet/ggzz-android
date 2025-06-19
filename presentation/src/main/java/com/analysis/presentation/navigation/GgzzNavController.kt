package com.analysis.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.analysis.presentation.feature.history.navigation.navigateToHistory
import com.analysis.presentation.feature.historydetail.navigation.navigateToResult
import com.analysis.presentation.feature.home.navigation.navigateToHome
import com.analysis.presentation.feature.login.navigation.navigateToLogin
import com.analysis.presentation.feature.personality.navigation.navigateToPersonality
import com.analysis.presentation.feature.setting.navigation.navigateToSetting
import com.analysis.presentation.feature.signup.navigation.navigateToSignUp
import com.analysis.presentation.feature.verify.navigation.navigateToVerify

internal class GgzzNavController(
    val navController: NavHostController,
) {
    val startDestination = NavRoute.Splash

    val isNavigationBarVisible: Boolean
        @Composable
        get() = NavTab.contains { navRoute ->
            currentDestination?.hasRoute(navRoute::class) == true
        }

    val currentTab: NavTab
        @Composable
        get() = NavTab.find { navRoute ->
            currentDestination?.hasRoute(navRoute::class) == true
        } ?: NavTab.HOME

    private val currentDestination: NavDestination?
        @Composable
        get() = navController.currentBackStackEntryAsState().value?.destination

    fun navigate(tab: NavTab) {
        val tabNavOptions = navOptions {
            popUpTo(navController.graph.id)
            launchSingleTop = true
        }

        when (tab) {
            NavTab.HOME -> navController.navigateToHome(tabNavOptions)
            NavTab.HISTORY -> navController.navigateToHistory(tabNavOptions)
            NavTab.SETTING -> navController.navigateToSetting(
                tabNavOptions,
            )
        }
    }

    fun navigateToLogin() {
        val navOptions = navOptions {
            popUpTo(navController.graph.id)
            launchSingleTop = true
        }

        navController.navigateToLogin(navOptions)
    }

    fun navigateToHome() {
        val navOptions = navOptions {
            popUpTo(navController.graph.id)
            launchSingleTop = true
        }

        navController.navigateToHome(navOptions)
    }

    fun navigateToSignUp() {
        val navOptions = navOptions {
            launchSingleTop = true
        }

        navController.navigateToSignUp(navOptions)
    }

    fun navigateToHistory() {
        navigate(NavTab.HISTORY)
    }

    fun navigateToComparisonVerify() {
        val navOptions = navOptions {
            launchSingleTop = true
        }
        navController.navigateToVerify(navOptions)
    }

    fun navigateToPersonality() {
        val navOptions = navOptions {
            launchSingleTop = true
        }
        navController.navigateToPersonality(navOptions)
    }

    fun navigateToResult(historyId: String) {
        val navOptions = navOptions {
            launchSingleTop = true
        }
        navController.navigateToResult(historyId, navOptions)
    }

    fun popBackStack() {
        navController.popBackStack()
    }
}
