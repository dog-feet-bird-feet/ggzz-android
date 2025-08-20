package com.analysis.presentation.feature.personality

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.analysis.presentation.R
import com.analysis.presentation.component.GgzzTopAppBar
import com.analysis.presentation.feature.personality.component.HandWritingUploadScreen
import com.analysis.presentation.feature.personality.component.ResultScreen
import com.analysis.presentation.feature.personality.model.PersonalityUiState
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Gray100
import com.analysis.presentation.theme.Gray900
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun PersonalityScreen(
    showErrorSnackBar: (Throwable) -> Unit,
    onClickNavigation: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: PersonalityViewModel = hiltViewModel(),
) {
    val personalityUiState by viewModel.personalityUiState.collectAsStateWithLifecycle()
    val selectedImageUri by viewModel.selectedImageUri.collectAsStateWithLifecycle()
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
            title = stringResource(R.string.personality_top_app_bar_title),
            textStyle = GgzzTheme.typography.pretendardRegular18.copy(color = Gray900),
            navigationIcon = {
                if (personalityUiState is PersonalityUiState.ImageUploadState) {
                    IconButton(onClick = onClickNavigation) {
                        Image(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null,
                        )
                    }
                }
            },
        )

        PersonalityScreenContent(
            personalityUiState,
            selectedImageUri,
            navigateToHome,
            { viewModel.updatePickedVerificationUri(it) },
            { viewModel.removeVerificationUri() },
            { viewModel.executeAnalysis() }
        )
    }
}

@Composable
private fun PersonalityScreenContent(
    personalityUiState: PersonalityUiState,
    selectedImageUri: Uri,
    navigateToHome: () -> Unit,
    onPickPhoto: (Uri) -> Unit = {},
    onClickCancelButton: () -> Unit,
    onClickAnalyzingButton: () -> Unit,
) {
    when (personalityUiState) {
        PersonalityUiState.ImageUploadState -> {
            HandWritingUploadScreen(
                onPickPhoto = onPickPhoto,
                onClickCancelButton = onClickCancelButton,
                onClickAnalyzingButton = onClickAnalyzingButton,
                selectedHandWritingUri = selectedImageUri,
            )
        }

        is PersonalityUiState.Analyzing -> ResultScreen(
            personalityUiState,
            onClickHomeButton = navigateToHome,
        )
    }
}


@Preview
@Composable
private fun PersonalityScreenContentPreview() {
    val personalityUiState = PersonalityUiState.ImageUploadState
    val selectedImageUri = Uri.EMPTY

    GgzzTheme {
        PersonalityScreenContent(
            personalityUiState = personalityUiState,
            selectedImageUri = selectedImageUri,
            navigateToHome = {},
            onPickPhoto = {},
            onClickCancelButton = {},
            onClickAnalyzingButton = {}
        )
    }
}
