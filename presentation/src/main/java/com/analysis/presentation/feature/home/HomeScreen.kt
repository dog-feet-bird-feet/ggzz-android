package com.analysis.presentation.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.analysis.presentation.R
import com.analysis.presentation.component.GgzzTopAppBar
import com.analysis.presentation.feature.home.component.HomeMenuCard
import com.analysis.presentation.theme.Gray100

@Composable
internal fun HomeScreen(
    navigateToAnalysis: () -> Unit,
    navigateToHistory: () -> Unit,
) {
    Scaffold(
        topBar = {
            GgzzTopAppBar(
                title = stringResource(R.string.home_top_app_bar_title),
            )
        },
        containerColor = Gray100,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            HomeMenuCard(
                R.drawable.ic_home_analysis,
                R.string.home_analysis_title,
                R.string.home_analysis_description,
                onClick = navigateToAnalysis,
            )
            Spacer(modifier = Modifier.height(24.dp))
            HomeMenuCard(
                R.drawable.ic_home_how_to_use,
                R.string.home_how_to_use_title,
                R.string.home_how_to_use_description,
            )
            Spacer(modifier = Modifier.height(24.dp))
            HomeMenuCard(
                R.drawable.ic_home_history,
                R.string.home_history_title,
                R.string.home_history_description,
                onClick = navigateToHistory,
            )
            Spacer(modifier = Modifier.height(24.dp))
            HomeMenuCard(
                R.drawable.ic_home_personality,
                R.string.home_personality_title,
                R.string.home_personality_description,
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun HomeScreenContentPreview() {
    HomeScreen(
        {},
        {},
    )
}
