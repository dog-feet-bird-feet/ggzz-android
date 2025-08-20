package com.analysis.presentation.feature.verify

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.IconButton
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

        viewModel.errorMsgResId.collectLatest { stringsId ->
            val msg = context.getString(stringsId)
            showErrorSnackBar(IllegalArgumentException(msg))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray100)
            .systemBarsPadding(),
    ) {
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

        VerifyScreenContent(
            uiState = uiState,
            selectedComparisonUris = selectedComparisonUris,
            selectedVerificationUri = selectedVerificationUri,
            onClickHomeButton = onClickHomeButton,
            onMoveToVerificationUpload = { viewModel.moveToVerificationUpload() },
            onUpdatePickedComparisonUris = { uris -> viewModel.updatePickedComparisonUris(uris) },
            onRemoveComparisonUri = { uri -> viewModel.removeComparisonUri(uri) },
            onMoveToComparisonUpload = { viewModel.moveToComparisonUpload() },
            onExecuteAnalysis = { viewModel.executeAnalysis() },
            onUpdatePickedVerificationUri = { uri -> viewModel.updatePickedVerificationUri(uri) },
            onRemoveVerificationUri = { viewModel.removeVerificationUri() },
        )
    }
}

@Composable
private fun VerifyScreenContent(
    uiState: VerificationUiState,
    selectedComparisonUris: List<Uri>,
    selectedVerificationUri: Uri,
    onClickHomeButton: () -> Unit,
    onMoveToVerificationUpload: () -> Unit,
    onUpdatePickedComparisonUris: (List<Uri>) -> Unit,
    onRemoveComparisonUri: (Uri) -> Unit,
    onMoveToComparisonUpload: () -> Unit,
    onExecuteAnalysis: () -> Unit,
    onUpdatePickedVerificationUri: (Uri) -> Unit,
    onRemoveVerificationUri: () -> Unit,
) {
    when (uiState) {
        VerificationUiState.ComparisonUploadState -> {
            ComparisonVerifyScreenContent(
                selectedComparisonUris = selectedComparisonUris,
                onClickNextButton = onMoveToVerificationUpload,
                updatePickedComparisonUris = onUpdatePickedComparisonUris,
                removeComparisonUri = onRemoveComparisonUri,
            )
        }

        VerificationUiState.VerificationUploadState -> {
            VerificationVerifyScreenContent(
                selectedVerificationUri = selectedVerificationUri,
                onClickPreviousButton = onMoveToComparisonUpload,
                onClickAnalysisButton = onExecuteAnalysis,
                onPickPhoto = onUpdatePickedVerificationUri,
                onClickCancelButton = onRemoveVerificationUri,
            )
        }

        is VerificationUiState.Verification -> {
            ResultScreen(
                uiState = uiState,
                onClickHomeButton = onClickHomeButton,
            )
        }
    }
}

@Preview
@Composable
private fun VerifyScreenContentPreview() {
    VerifyScreenContent(
        uiState = VerificationUiState.ComparisonUploadState,
        selectedComparisonUris = listOf(Uri.EMPTY, Uri.EMPTY),
        selectedVerificationUri = Uri.EMPTY,
        onClickHomeButton = {},
        onMoveToVerificationUpload = {},
        onUpdatePickedComparisonUris = {},
        onRemoveComparisonUri = {},
        onMoveToComparisonUpload = {},
        onExecuteAnalysis = {},
        onUpdatePickedVerificationUri = {},
        onRemoveVerificationUri = {},
    )
}
