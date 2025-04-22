package com.analysis.presentation.feature.verify.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.analysis.presentation.theme.Gray300

@Composable
internal fun StepCard(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    containerColor: Color = Gray300,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors().copy(containerColor = containerColor),
        shape = RoundedCornerShape(30.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                style = style,
            )
        }
    }
}
