package com.analysis.presentation.personality

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import com.analysis.presentation.personality.component.HandWritingUploadScreen
import com.analysis.presentation.personality.component.ResultLoadingScreen
import com.analysis.presentation.personality.component.ResultScreen
import com.analysis.presentation.personality.model.PersonalityUiState
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Gray100
import com.analysis.presentation.theme.Gray900
import com.analysis.presentation.util.ImageUtil

@Composable
fun PersonalityScreen(
    showErrorSnackBar: (Throwable) -> Unit,
    onClickNavigation: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: PersonalityViewModel = hiltViewModel(),
) {
    val personalityUiState by viewModel.personalityUiState.collectAsStateWithLifecycle()
    val selectedImageUri by viewModel.selectedImageUri.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val contentResolver = context.contentResolver

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
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
        },
        containerColor = Gray100,
    ) { innerPadding ->

        when (personalityUiState) {
            PersonalityUiState.ImageUploadState -> {
                HandWritingUploadScreen(
                    innerPadding = innerPadding,
                    showErrorSnackBar = showErrorSnackBar,
                    onPickPhoto = { viewModel.updatePickedVerificationUri(it) },
                    onClickCancelButton = { viewModel.removeVerificationUri() },
                    onClickAnalyzingButton = {
                        val imageUri =
                            selectedImageUri ?: throw IllegalStateException("이미지가 선택되지 않았습니다.")
                        val imageMultipart = ImageUtil.uriToMultipart(
                            partName = "personality-file",
                            uri = imageUri,
                            resolver = contentResolver,
                        )

                        viewModel.executeAnalysis(imageMultipart)
                    },
                    selectedHandWritingUri = selectedImageUri,
                )
            }

            PersonalityUiState.Loading -> ResultLoadingScreen(innerPadding)
            is PersonalityUiState.ResultUiState -> ResultScreen(
                innerPadding,
                personalityUiState as PersonalityUiState.ResultUiState,
                onClickHomeButton = navigateToHome,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PersonalityScreenPreview() {
    PersonalityScreen(
        {},
        {},
        {},
    )
}
