package com.analysis.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Gray300

@Composable
internal fun CustomLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    progressColor: Color,
    backgroundColor: Color,
    clipShape: Shape = RoundedCornerShape(10.dp),
) {
    Box(
        modifier = modifier
            .clip(clipShape)
            .background(backgroundColor)
            .height(8.dp),
    ) {
        Box(
            modifier = Modifier
                .clip(clipShape)
                .background(progressColor)
                .fillMaxHeight()
                .fillMaxWidth(progress),
        )
    }
}

@Composable
@Preview(showSystemUi = true)
private fun CustomLinearProgressIndicatorPreview() {
    Column {
        CustomLinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = 0.3f,
            progressColor = GgzzTheme.colorScheme.primary,
            backgroundColor = Gray300,
        )
    }
}
