package com.analysis.presentation.feature.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.analysis.presentation.R
import com.analysis.presentation.component.GgzzTextField
import com.analysis.presentation.component.GgzzTopAppBar
import com.analysis.presentation.component.rememberSaveableGgzzTextFieldState
import com.analysis.presentation.theme.Blue300
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Gray100
import com.analysis.presentation.theme.Gray500
import com.analysis.presentation.theme.Purple700
import com.analysis.presentation.theme.White
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    showErrorSnackBar: (Throwable) -> Unit,
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val emailGgzzTextFieldState = rememberSaveableGgzzTextFieldState(
        placeholder = "이메일 입력",
    )
    val passwordGgzzTextFieldState = rememberSaveableGgzzTextFieldState(
        placeholder = "비밀번호 입력",
    )

    val isFormValid = !emailGgzzTextFieldState.isError && !passwordGgzzTextFieldState.isError
    val isLoginSuccess by viewModel.isLoginSuccess.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.error.collectLatest { showErrorSnackBar(it) }
    }

    LaunchedEffect(isLoginSuccess) {
        if (isLoginSuccess) {
            navigateToHome()
        }
    }

    Scaffold(
        topBar = {
            GgzzTopAppBar(
                title = stringResource(R.string.home_top_app_bar_title),
            )
        },
        containerColor = Gray100,
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(8.dp),
            color = White,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                Text(
                    text = "로그인",
                    style = GgzzTheme.typography.pretendardBold42.copy(color = Blue300),
                )

                Spacer(modifier = Modifier.height(40.dp))

                GgzzTextField(
                    state = emailGgzzTextFieldState
                )

                Spacer(modifier = Modifier.height(10.dp))

                GgzzTextField(
                    state = passwordGgzzTextFieldState
                )

                Spacer(modifier = Modifier.height(35.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                        .height(55.dp),
                    onClick = {
                        viewModel.login(
                            emailGgzzTextFieldState.text,
                            passwordGgzzTextFieldState.text
                        )
                    },
                    enabled = isFormValid,
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonColors(
                        containerColor = Purple700,
                        contentColor = White,
                        disabledContentColor = White,
                        disabledContainerColor = Gray500,
                    ),
                ) {
                    Text(
                        text = "로그인",
                        style = GgzzTheme.typography.pretendardSemiBold14.copy(color = White),
                    )
                }

                Spacer(modifier = Modifier.height(63.dp))

                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 40.dp),
                    color = Gray500,
                    thickness = 1.dp,
                )

                Spacer(modifier = Modifier.height(23.dp))

                Text(
                    text = "계정이 없으신가요?",
                    style = GgzzTheme.typography.pretendardBold24.copy(color = Blue300),
                )

                Spacer(modifier = Modifier.height(23.dp))

                Text(
                    modifier = Modifier.clickable { navigateToSignUp() },
                    text = "회원가입",
                    style = GgzzTheme.typography.pretendardMedium16,
                    textDecoration = TextDecoration.Underline,
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun LoginScreenPreview() {
    LoginScreen({}, {}, {})
}
