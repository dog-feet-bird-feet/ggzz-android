package com.analysis.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal object GgzzColorScheme {
    val primary: Color = Purple700
}

private val LocalColorScheme = staticCompositionLocalOf { GgzzColorScheme }

private val LocalTypography = staticCompositionLocalOf { GgzzTypography }

internal object GgzzTheme {
    val colorScheme: GgzzColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: GgzzTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

@Composable
internal fun GgzzTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    typography: GgzzTypography = GgzzTheme.typography,
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
            darkTheme -> GgzzColorScheme
            else -> GgzzColorScheme
        }

    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalTypography provides typography,
    ) {
        MaterialTheme(
            content = content,
        )
    }
}
