package com.analysis.presentation.util.modifier

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection

@Composable
fun Modifier.defaultScreenPadding(innerPadding: PaddingValues): Modifier {
    val topFromScaffold = innerPadding.calculateTopPadding()
    val startFromScaffold = innerPadding.calculateStartPadding(LocalLayoutDirection.current)
    val endFromScaffold = innerPadding.calculateEndPadding(LocalLayoutDirection.current)
    val bottomFromScaffold = innerPadding.calculateBottomPadding()

    // 시스템 내비게이션 바(inset)에서 bottom inset만 꺼낸다
    val navBarInsetBottom = WindowInsets
        .navigationBars
        .only(WindowInsetsSides.Bottom)
        .asPaddingValues()
        .calculateBottomPadding()

    // 4) Modifier.padding(...)으로 한 번에 리턴
    return this.padding(
        start = startFromScaffold,
        top = topFromScaffold,
        end = endFromScaffold,
        bottom = bottomFromScaffold + navBarInsetBottom,
    )
}
