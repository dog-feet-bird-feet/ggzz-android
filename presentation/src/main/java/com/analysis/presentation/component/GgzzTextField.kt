package com.analysis.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.Gray500

@Composable
internal fun GgzzTextField(
    state: GgzzTextFieldState,
    isSecret: Boolean = false,
    trailingIcon: @Composable () -> Unit = {},
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
        value = state.textFieldValue,
        onValueChange = { state.updateText(it) },
        placeholder = {
            Text(
                text = state.placeholder ?: "",
                style = GgzzTheme.typography.pretendardRegular12.copy(color = Gray500),
            )
        },
        trailingIcon = { trailingIcon() },
        visualTransformation = if (isSecret) PasswordVisualTransformation() else VisualTransformation.None,
        singleLine = true,
        shape = RoundedCornerShape(5.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Gray500,
            unfocusedBorderColor = Gray500,
        ),
    )
}

@Composable
@Preview
fun GgzzTextFieldPreview(modifier: Modifier = Modifier) {
    GgzzTextField(
        state = GgzzTextFieldState(
            text = "안녕하세요",
            maxLength = 10,
        ),
    )
}
