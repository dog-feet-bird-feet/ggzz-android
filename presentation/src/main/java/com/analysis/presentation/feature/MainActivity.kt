package com.analysis.presentation.feature

import android.content.Context
import android.content.Intent
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
    private val hasAccessToken by lazy {
        intent.getBooleanExtra(
            HAS_ACCESS_TOKEN_KEY,
            false,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val ggzzNavController = GgzzNavController(navController)

            GgzzTheme {
                MainScreen(hasAccessToken, ggzzNavController)
            }
        }
    }

    companion object {
        const val HAS_ACCESS_TOKEN_KEY = "has_access_token_key"

        fun startActivity(
            context: Context,
            hasAccessToken:Boolean,
        ) {
            val intent = Intent(context, MainActivity::class.java).apply {
                putExtra(HAS_ACCESS_TOKEN_KEY, hasAccessToken)
            }
            context.startActivity(intent)
        }
    }
}
