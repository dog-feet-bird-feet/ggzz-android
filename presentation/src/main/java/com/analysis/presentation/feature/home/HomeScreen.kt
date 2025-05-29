package com.analysis.presentation.feature.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    navigateToPersonality: () -> Unit,
) {
    val context = LocalContext.current

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
            ) {
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://positive-printer-b18.notion.site/1f456972e71080e49ce1cc2b49ae2415?pvs=4"))
                context.startActivity(intent)
            }
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
                onClick = navigateToPersonality,
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
        {},
    )
}
