package com.analysis.presentation.feature.handwritinganalysis

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun HandWritingAnalysisScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(text = "HandWritingAnalysis")
    }
}
