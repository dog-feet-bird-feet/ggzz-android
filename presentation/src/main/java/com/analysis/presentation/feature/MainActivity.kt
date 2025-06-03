package com.analysis.presentation.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.analysis.presentation.feature.main.MainScreen
import com.analysis.presentation.navigation.GgzzNavController
import com.analysis.presentation.theme.GgzzTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val ggzzNavController = GgzzNavController(navController)

            GgzzTheme {
                MainScreen(ggzzNavController)
            }
        }
    }
}
