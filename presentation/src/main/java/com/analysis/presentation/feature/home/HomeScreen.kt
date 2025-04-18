package com.analysis.presentation.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(text = "Home")
    }
}
