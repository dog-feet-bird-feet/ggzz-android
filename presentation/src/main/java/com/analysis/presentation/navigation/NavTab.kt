package com.analysis.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.analysis.presentation.R

internal enum class NavTab(
    @DrawableRes val iconResId: Int,
    @StringRes val titleTextId: Int,
    val route: NavRoute,
) {
    HOME(
        iconResId = R.drawable.ic_home,
        titleTextId = R.string.home_screen_title,
        route = NavRoute.Home,
    ),
    HISTORY(
        iconResId = R.drawable.ic_history,
        titleTextId = R.string.history_screen_title,
        route = NavRoute.History,
    ),
    SETTING(
        iconResId = R.drawable.ic_setting,
        titleTextId = R.string.setting_screen_title,
        route = NavRoute.Setting,
    ),
    ;

    companion object {
        @Composable
        fun find(isRouteMatch: @Composable (NavRoute) -> Boolean): NavTab? {
            return entries.find { isRouteMatch(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (NavRoute) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
