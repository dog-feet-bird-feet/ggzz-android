package com.analysis.presentation.feature.setting

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.analysis.presentation.R
import com.analysis.presentation.component.GgzzTopAppBar
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Gray100
import com.analysis.presentation.theme.Gray900
import com.analysis.presentation.theme.White
import com.analysis.presentation.util.modifier.dropShadow
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun SettingScreen(
    defaultPadding: PaddingValues,
    navigateToLogin: () -> Unit,
    viewModel: SettingViewModel = hiltViewModel()
) {
    val isLogoutSuccess by viewModel.isLogoutSuccess.collectAsStateWithLifecycle()


    LaunchedEffect(isLogoutSuccess) {
        if (isLogoutSuccess) {
            navigateToLogin()
        }
    }

    Scaffold(
        topBar = {
            GgzzTopAppBar(
                title = stringResource(R.string.setting_top_app_bar_title),
            )
        },
        containerColor = Gray100,
    ) { innerPadding ->
        SettingScreenContent(
            modifier = Modifier
                .padding(defaultPadding)
                .padding(innerPadding),
            { viewModel.logout() },
        )
    }
}

@Composable
private fun SettingScreenContent(
    modifier: Modifier = Modifier,
    logout: () -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier = modifier,
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .dropShadow(
                    shape = RoundedCornerShape(10),
                    offsetX = 2.dp,
                    offsetY = 2.dp,
                    blur = 10.dp,
                    color = Color.Black.copy(0.05f),
                ),
            colors = CardDefaults.cardColors(
                containerColor = White,
            ),
            shape = RoundedCornerShape(10),
            onClick = {
                val intent =
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(
                            "https://url.kr/5at64l",
                        ),
                    )
                context.startActivity(intent)
            },
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(17.dp),
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "문의하기",
                    style = GgzzTheme.typography.pretendardMedium18.copy(color = Gray900),
                )

                Image(
                    painter = painterResource(R.drawable.ic_arrow_right),
                    contentDescription = null,
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .dropShadow(
                    shape = RoundedCornerShape(10),
                    offsetX = 2.dp,
                    offsetY = 2.dp,
                    blur = 10.dp,
                    color = Color.Black.copy(0.05f),
                ),
            colors = CardDefaults.cardColors(
                containerColor = White,
            ),
            shape = RoundedCornerShape(10),
            onClick = { logout() },
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(17.dp),
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "로그아웃",
                    style = GgzzTheme.typography.pretendardMedium18.copy(color = Gray900),
                )

                Image(
                    painter = painterResource(R.drawable.ic_arrow_right),
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SettingScreenContentPreview(modifier: Modifier = Modifier) {
    SettingScreenContent(logout = {})
}
