package com.analysis.presentation.feature.verify

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.analysis.presentation.R
import com.analysis.presentation.component.GgzzTopAppBar
import com.analysis.presentation.feature.verify.component.ComparisonVerifyScreenContent
import com.analysis.presentation.feature.verify.component.ResultScreen
import com.analysis.presentation.feature.verify.component.VerificationVerifyScreenContent
import com.analysis.presentation.feature.verify.model.VerificationUiState
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Gray100
import com.analysis.presentation.theme.Gray900
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun VerifyScreen(
    showErrorSnackBar: (Throwable) -> Unit,
    onClickNavigation: () -> Unit,
    onClickHomeButton: () -> Unit,
    viewModel: VerifyViewModel = hiltViewModel(),
) {
    val selectedComparisonUris by viewModel.selectedComparisonUris.collectAsStateWithLifecycle()
    val selectedVerificationUri by viewModel.selectedVerificationUri.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.error.collectLatest { showErrorSnackBar(it) }
    }

    LaunchedEffect(Unit) {
        viewModel.errorMsgResId.collectLatest { stringsId ->
            val msg = context.getString(stringsId)
            showErrorSnackBar(IllegalArgumentException(msg))
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            GgzzTopAppBar(
                title = stringResource(R.string.verify_top_app_bar_title),
                textStyle = GgzzTheme.typography.pretendardRegular18.copy(color = Gray900),
                navigationIcon = {
                    if (uiState !is VerificationUiState.Verification.Loading) {
                        IconButton(onClick = onClickNavigation) {
                            Image(
                                painter = painterResource(R.drawable.ic_arrow_back),
                                contentDescription = null,
                            )
                        }
                    }
                },
            )
        },
        containerColor = Gray100,
    ) { innerPadding ->

        when (uiState) {
            VerificationUiState.ComparisonUploadState -> {
                ComparisonVerifyScreenContent(
                    innerPadding = innerPadding,
                    selectedComparisonUris = selectedComparisonUris,
                    viewModel = viewModel,
                    onClickNextButton = { viewModel.moveToVerificationUpload() },
                )
            }

            VerificationUiState.VerificationUploadState -> {
                VerificationVerifyScreenContent(
                    innerPadding = innerPadding,
                    viewModel = viewModel,
                    selectedVerificationUri = selectedVerificationUri,
                    onClickPreviousButton = { viewModel.moveToComparisonUpload() },
                    onClickAnalysisButton = { viewModel.executeAnalysis() },
                )
            }

            is VerificationUiState.Verification -> {
                ResultScreen(
                    innerPadding = innerPadding,
                    uiState = uiState as VerificationUiState.Verification,
                    onClickHomeButton = { onClickHomeButton() },
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ComparisonVerifyScreenPreview() {
    VerifyScreen({}, {}, {})
}
