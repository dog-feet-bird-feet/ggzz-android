package com.analysis.presentation.feature.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun SettingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(text = "Setting")
    }
}
