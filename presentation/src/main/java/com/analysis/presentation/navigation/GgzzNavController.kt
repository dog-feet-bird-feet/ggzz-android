package com.analysis.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.analysis.presentation.feature.verify.navigation.navigateToComparisonVerify
import com.analysis.presentation.feature.history.navigation.navigateToHistory
import com.analysis.presentation.feature.home.navigation.navigateToHome
import com.analysis.presentation.feature.setting.navigation.navigateToSetting

internal class GgzzNavController(
    val navController: NavHostController,
) {
    val startDestination = NavRoute.Home

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
            popUpTo(navController.graph.id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            NavTab.HOME -> navController.navigateToHome(tabNavOptions)
            NavTab.HISTORY -> navController.navigateToHistory(tabNavOptions)
            NavTab.SETTING -> navController.navigateToSetting(
                tabNavOptions,
            )
        }
    }

    fun navigateToHistory() {
        navigate(NavTab.HISTORY)
    }

    fun navigateToComparisonVerify() {
        val navOptions = navOptions {
            launchSingleTop = true
        }
        navController.navigateToComparisonVerify(navOptions)
    }
}
