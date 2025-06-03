package com.analysis.presentation.feature.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.analysis.presentation.R
import com.analysis.presentation.theme.White

@Composable
fun SplashScreen(
    isPreWorkEnd: Boolean?,
    onStartEvent: () -> Unit,
    onSplashEndEvent: () -> Unit,
    animationDelayMillis: Int = 1500,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var isAnimationEnd by rememberSaveable { mutableStateOf(false) }
    val alpha = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = Unit) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(animationDelayMillis),
        )
        isAnimationEnd = true
    }

    LaunchedEffect(isPreWorkEnd, isAnimationEnd) {
        if (isPreWorkEnd != null && isAnimationEnd) {
            onSplashEndEvent()
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                onStartEvent()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Surface(
        color = White
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                modifier = Modifier.alpha(alpha.value),
                painter = painterResource(R.drawable.ic_ggzz_splash),
                contentDescription = null,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SplashScreenPreview(modifier: Modifier = Modifier) {
//    SplashScreen({})
}
