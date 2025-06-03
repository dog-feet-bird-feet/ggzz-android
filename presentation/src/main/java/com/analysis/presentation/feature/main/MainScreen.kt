package com.analysis.presentation.feature.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.analysis.presentation.R
import com.analysis.presentation.component.GgzzNavigationBar
import com.analysis.presentation.navigation.GgzzNavController
import com.analysis.presentation.navigation.GgzzNavHost
import com.analysis.presentation.navigation.NavRoute
import com.analysis.presentation.navigation.NavTab
import com.analysis.presentation.theme.GgzzTheme
import kotlinx.coroutines.launch

@Composable
internal fun MainScreen(
    navController: GgzzNavController,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val snackBarHostState = remember { SnackbarHostState() }
    val showErrorSnackbar: (Throwable) -> Unit = { throwable ->
        coroutineScope.launch {
            val message = throwable.message ?: context.getString(R.string.unknown_error_snackbar)
            snackBarHostState.showSnackbar(message)
        }
    }

    MainContent(navController, snackBarHostState, showErrorSnackbar)
}

@Composable
private fun MainContent(
    navController: GgzzNavController,
    snackBarHostState: SnackbarHostState,
    showErrorSnackbar: (Throwable) -> Unit,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val hasAccessToken by viewModel.hasAccessToken.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        snackbarHost = { SnackbarHost(snackBarHostState) },
        bottomBar = {
            GgzzNavigationBar(
                isVisible = navController.isNavigationBarVisible,
                currentTab = navController.currentTab,
                tabs = NavTab.entries,
                onTabClick = {
                    navController.navigate(it)
                },
            )
        },
    ) { innerPadding ->
        GgzzNavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = navController.startDestination,
            isPreWorkEnd = hasAccessToken,
            onStartEvent = { viewModel.getAccessTokenStatus() },
            onSplashEndEvent = {
                hasAccessToken?.let {
                    if(it) navController.navigateToHome()
                    else navController.navigateToLogin()
                }
            },
            showErrorSnackbar = showErrorSnackbar,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MainContentPreview() {
    val navController = rememberNavController()
    val jipmoNavController = GgzzNavController(navController)
    val snackBarHostState = remember { SnackbarHostState() }

    GgzzTheme {
//        MainContent(
//            navController = jipmoNavController,
//            true,
//            snackBarHostState = snackBarHostState,
//            showErrorSnackbar = {},
//        )
    }
}
