package com.analysis.presentation.navigation

import kotlinx.serialization.Serializable

internal sealed interface NavRoute {
    @Serializable
    data object Splash : NavRoute

    @Serializable
    data object SignUp : NavRoute

    @Serializable
    data object Login : NavRoute

    @Serializable
    data object Home : NavRoute

    @Serializable
    data object History : NavRoute

    @Serializable
    data object Setting : NavRoute

    @Serializable
    data object Verify : NavRoute

    @Serializable
    data class HistoryDetail(val historyId: String) : NavRoute

    @Serializable
    data object Personality : NavRoute
}
