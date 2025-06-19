package com.analysis.presentation.component

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.analysis.presentation.navigation.GgzzNavController
import com.analysis.presentation.navigation.GgzzNavHost
import com.analysis.presentation.navigation.NavRoute
import com.analysis.presentation.navigation.NavTab
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.GgzzTypography
import com.analysis.presentation.theme.Gray600
import com.analysis.presentation.theme.White
import com.analysis.presentation.util.NoRippleInteractionSource
import com.analysis.presentation.util.modifier.dropShadow

@Composable
internal fun GgzzNavigationBar(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    tabs: List<NavTab> = NavTab.entries,
    currentTab: NavTab,
    onTabClick: (NavTab) -> Unit,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = ExitTransition.None,
    ) {
        NavigationBar(
            modifier = modifier
                .fillMaxWidth()
                .height(73.dp)
                .dropShadow(
                    shape = RectangleShape,
                    color = Color.Black.copy(0.1f),
                    blur = 10.dp,
                    offsetX = 1.dp,
                ),
            containerColor = White,
        ) {
            tabs.forEach { tab ->
                GgzzNavigationBarItem(
                    tab = tab,
                    selected = tab == currentTab,
                    onTabClick = { onTabClick(it) },
                )
            }
        }
    }
}

@Composable
private fun RowScope.GgzzNavigationBarItem(
    tab: NavTab,
    selected: Boolean,
    onTabClick: (NavTab) -> Unit,
) {
    NavigationBarItem(
        modifier = Modifier.padding(horizontal = 8.dp),
        icon = {
            Icon(
                painter = painterResource(tab.iconResId),
                tint = navigationBarItemColor(selected),
                contentDescription = null,
            )
        },
        label = {
            Text(
                text = stringResource(tab.titleTextId),
                style = GgzzTypography.pretendardRegular14,
                color = navigationBarItemColor(selected),
            )
        },
        selected = selected,
        onClick = { onTabClick(tab) },
        colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
        interactionSource = NoRippleInteractionSource,
    )
}

@Composable
private fun navigationBarItemColor(selected: Boolean): Color {
    if (selected) {
        return GgzzTheme.colorScheme.primary
    }
    return Gray600
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showSystemUi = true)
@Composable
private fun GgzzNavigationBarPreview() {
    val navController = rememberNavController()
    val ggzzNavController = GgzzNavController(navController)

    GgzzTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            containerColor = White,
            bottomBar = {
                GgzzNavigationBar(
                    isVisible = true,
                    currentTab = ggzzNavController.currentTab,
                    tabs = NavTab.entries,
                    onTabClick = {
                        ggzzNavController.navigate(it)
                    },
                )
            },
        ) { innerPadding ->
            GgzzNavHost(
                defaultPadding = PaddingValues(20.dp),
                navController = ggzzNavController,
                startDestination = NavRoute.Home,
                showErrorSnackBar = {},
                isPreWorkEnd = true,
                onStartEvent = {},
                onSplashEndEvent = {},
            )
        }
    }
}
