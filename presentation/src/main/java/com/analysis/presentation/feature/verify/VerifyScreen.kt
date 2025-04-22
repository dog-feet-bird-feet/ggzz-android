package com.analysis.presentation.feature.verify

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.analysis.presentation.R
import com.analysis.presentation.component.GgzzTopAppBar
import com.analysis.presentation.feature.verify.component.ComparisonVerifyScreenContent
import com.analysis.presentation.feature.verify.component.VerificationVerifyScreenContent
import com.analysis.presentation.feature.verify.model.UploadState
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Gray100
import com.analysis.presentation.theme.Gray900

@Composable
internal fun VerifyScreen(
    showErrorSnackBar: (Throwable) -> Unit,
    onClickNavigation: () -> Unit,
    viewModel: VerifyViewModel = hiltViewModel(),
) {
    val selectedComparisonUris by viewModel.selectedComparisonUris.collectAsStateWithLifecycle()
    val selectedVerificationUri by viewModel.selectedVerificationUri.collectAsStateWithLifecycle()
    val uploadState by viewModel.uploadState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            GgzzTopAppBar(
                title = stringResource(R.string.verify_top_app_bar_title),
                textStyle = GgzzTheme.typography.pretendardRegular18.copy(color = Gray900),
                navigationIcon = {
                    IconButton(onClick = onClickNavigation) {
                        Image(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null,
                        )
                    }
                }
            )
        },
        containerColor = Gray100
    ) { innerPadding ->

        when (uploadState) {
            UploadState.ComparisonUploadState -> {
                ComparisonVerifyScreenContent(
                    innerPadding = innerPadding,
                    selectedComparisonUris = selectedComparisonUris,
                    viewModel = viewModel,
                    showErrorSnackBar = showErrorSnackBar,
                    onClickNextButton = { viewModel.changeUploadState() }
                )
            }

            UploadState.VerificationUploadState -> {
                VerificationVerifyScreenContent(
                    innerPadding = innerPadding,
                    viewModel = viewModel,
                    showErrorSnackBar = showErrorSnackBar,
                    selectedVerificationUri = selectedVerificationUri,
                    onClickPreviousButton = { viewModel.changeUploadState() },
                    onClickNextButton = { }
                )
            }
        }

    }
}


@Composable
@Preview(showBackground = true)
fun ComparisonVerifyScreenPreview() {
    VerifyScreen({}, {})
}
