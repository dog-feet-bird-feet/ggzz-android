package com.analysis.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.analysis.presentation.R
import com.analysis.presentation.theme.GgzzTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GgzzTopAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable () -> Unit = {},
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
        ),
        title = {
            Text(
                text = title,
                style = GgzzTypography.pretendardBold28,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        navigationIcon = { navigationIcon() },
        actions = { actions() },
    )
}

@Preview(showSystemUi = true)
@Composable
private fun GgzzTopAppBarPreview() {
    Column {
        GgzzTopAppBar(
            title = "끄적",
            navigationIcon = {
                IconButton(onClick = { /* do something */ }) {
                    Image(
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = null,
                    )
                }
            },
        )
    }
}
