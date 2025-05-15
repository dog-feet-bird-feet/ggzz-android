package com.analysis.presentation.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.analysis.presentation.theme.Black
import com.analysis.presentation.theme.GgzzTheme
import com.analysis.presentation.theme.White

@Composable
fun GgzzAlertDialog(
    dialogTitle: String,
    text: String,
    showModifyDialog: Boolean,
    onDismissRequest: (Boolean) -> Unit,
    onClickConfirm: (String) -> Unit,
) {
    var newText by rememberSaveable { mutableStateOf(text) }

    if (showModifyDialog) {
        AlertDialog(
            onDismissRequest = { onDismissRequest(false) },
            title = {
                Text(
                    dialogTitle,
                    style = GgzzTheme.typography.pretendardRegular18.copy(color = Black),
                )
            },
            text = {
                TextField(
                    value = newText,
                    onValueChange = { newText = it },
                    singleLine = true,
                    textStyle = GgzzTheme.typography.pretendardRegular16.copy(color = Black),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = White,
                        focusedContainerColor = White,
                        disabledContainerColor = White
                    )
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onClickConfirm(newText)
                        onDismissRequest(false)
                    }
                ) {
                    Text(
                        "확인",
                        style = GgzzTheme.typography.pretendardRegular14.copy(color = Black),
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { onDismissRequest(false) },
                ) {
                    Text(
                        "취소",
                        style = GgzzTheme.typography.pretendardRegular14.copy(color = Black),
                    )
                }
            },
            containerColor = White
        )
    }
}


@Composable
@Preview(showBackground = true)
fun GgzzAlertDialogPreview(modifier: Modifier = Modifier) {
    GgzzAlertDialog(
        "제목 수정하기",
        "Test1234",
        true,
        {},
        {}
    )
}
