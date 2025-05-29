package com.analysis.presentation.feature.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.analysis.presentation.R
import com.analysis.presentation.component.GgzzTopAppBar
import com.analysis.presentation.feature.MainActivity
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.White
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GgzzTheme {
                SplashScreen(
                    {finish()}
                )
            }
        }
    }

}

@Composable
fun SplashScreen(
    finish: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val hasAccessToken by viewModel.hasAccessToken.collectAsStateWithLifecycle()

    val alpha = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = Unit) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(1500)
        )

        MainActivity.startActivity(context, hasAccessToken)
        delay(200L)
        finish()
    }


    Scaffold(
        containerColor = White,
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
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
    SplashScreen({})
}
