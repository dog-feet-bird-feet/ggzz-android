package com.analysis.presentation.feature.signup

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.analysis.presentation.theme.Green400
import com.analysis.presentation.theme.Purple700
import com.analysis.presentation.theme.Red600
import com.analysis.presentation.theme.White
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignUpScreen(
    showErrorSnackBar: (Throwable) -> Unit,
    onClickNavigation: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val emailGgzzTextFieldState = rememberSaveableGgzzTextFieldState(
        placeholder = stringResource(R.string.signup_email_placeholder),
        validate = { Patterns.EMAIL_ADDRESS.matcher(it).matches() },
    )

    val passwordGgzzTextFieldState = rememberSaveableGgzzTextFieldState(
        placeholder = stringResource(R.string.signup_password_placeholder),
        onValueChange = { viewModel.isValidPassword(it) },
    )

    val passwordConfirmGgzzTextFieldState = rememberSaveableGgzzTextFieldState(
        placeholder = stringResource(R.string.signup_confirmed_password_placeholder),
        onValueChange = { viewModel.isValidConfirmedPassword(passwordGgzzTextFieldState.text, it) },
    )

    val isEmailAvailable by viewModel.isEmailAvailable.collectAsStateWithLifecycle()
    val isPasswordAvailable by viewModel.isPasswordAvailable.collectAsStateWithLifecycle()
    val isConfirmedPasswordAvailable by viewModel.isConfirmedPasswordAvailable.collectAsStateWithLifecycle()
    val isFormAvailable by viewModel.isFormAvailable.collectAsStateWithLifecycle()
    val isSignUpSuccess by viewModel.isSignUpSuccess.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.error.collectLatest { showErrorSnackBar(it) }
    }

    LaunchedEffect(isSignUpSuccess) {
        if (isSignUpSuccess) {
            navigateToHome()
        }
    }

    Scaffold(
        topBar = {
            GgzzTopAppBar(
                title = stringResource(R.string.home_top_app_bar_title),
                navigationIcon = {
                    IconButton(onClick = onClickNavigation) {
                        Image(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null,
                        )
                    }
                },
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
                    text = stringResource(R.string.signup_text),
                    style = GgzzTheme.typography.pretendardBold42.copy(color = Blue300),
                )

                Spacer(modifier = Modifier.height(40.dp))

                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 40.dp),
                            text = stringResource(R.string.signup_email_text),
                            style = GgzzTheme.typography.pretendardSemiBold16.copy(color = Blue300),
                        )

                        Spacer(modifier = Modifier.width(7.dp))

                        if (isEmailAvailable) {
                            Text(
                                text = stringResource(R.string.signup_available_email_text),
                                style = GgzzTheme.typography.pretendardRegular10.copy(color = Green400),
                            )
                        } else if (emailGgzzTextFieldState.text.isNotBlank())
                            {
                                Text(
                                    text = stringResource(R.string.signup_not_available_email_text),
                                    style = GgzzTheme.typography.pretendardRegular10.copy(color = Red600),
                                )
                            }
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    GgzzTextField(
                        state = emailGgzzTextFieldState,
                        trailingIcon = {
                            if (!emailGgzzTextFieldState.isError) {
                                Text(
                                    modifier = Modifier
                                        .padding(end = 7.dp)
                                        .clickable(
                                            onClick = { viewModel.checkEmail(emailGgzzTextFieldState.text) },
                                        ),
                                    text = stringResource(R.string.signup_check_duplication),
                                    style = GgzzTheme.typography.pretendardMedium12,
                                )
                            }
                        },
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 40.dp),
                            text = stringResource(R.string.signup_password_text),
                            style = GgzzTheme.typography.pretendardSemiBold16.copy(color = Blue300),
                        )

                        Spacer(modifier = Modifier.width(7.dp))

                        if (isPasswordAvailable) {
                            Text(
                                text = stringResource(R.string.signup_available_password_text),
                                style = GgzzTheme.typography.pretendardRegular10.copy(color = Green400),
                            )
                        } else if (passwordGgzzTextFieldState.text.isNotBlank())
                            {
                                Text(
                                    text = stringResource(R.string.signup_not_available_password_text),
                                    style = GgzzTheme.typography.pretendardRegular10.copy(color = Red600),
                                )
                            }
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    GgzzTextField(
                        state = passwordGgzzTextFieldState,
                        isSecret = true,
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 40.dp),
                            text = stringResource(R.string.signup_confirmed_password_text),
                            style = GgzzTheme.typography.pretendardSemiBold16.copy(color = Blue300),
                        )

                        Spacer(modifier = Modifier.width(7.dp))

                        if (isConfirmedPasswordAvailable) {
                            Text(
                                text = stringResource(R.string.signup_available_confirmed_password_text),
                                style = GgzzTheme.typography.pretendardRegular10.copy(color = Green400),
                            )
                        } else if (passwordConfirmGgzzTextFieldState.text.isNotBlank()) {
                            Text(
                                text = stringResource(R.string.signup_not_available_confirmed_password_text),
                                style = GgzzTheme.typography.pretendardRegular10.copy(color = Red600),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    GgzzTextField(
                        state = passwordConfirmGgzzTextFieldState,
                        isSecret = true,
                    )
                }

                Spacer(modifier = Modifier.height(35.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                        .height(55.dp),
                    onClick = {
                        viewModel.signUp(
                            emailGgzzTextFieldState.text,
                            passwordGgzzTextFieldState.text,
                        )
                    },
                    enabled = isFormAvailable,
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonColors(
                        containerColor = Purple700,
                        contentColor = White,
                        disabledContentColor = White,
                        disabledContainerColor = Gray500,
                    ),
                ) {
                    Text(
                        text = stringResource(R.string.signup_text),
                        style = GgzzTheme.typography.pretendardSemiBold14.copy(color = White),
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun SignUpScreenPreview(modifier: Modifier = Modifier) {
    SignUpScreen({}, {}, {})
}
