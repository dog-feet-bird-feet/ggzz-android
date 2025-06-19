package com.analysis.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

@Stable
internal class GgzzTextFieldState(
    textFieldValue: TextFieldValue = TextFieldValue(),
    val onValueChange: (String) -> Unit = {},
    val placeholder: String? = null,
    val maxLength: Int? = null,
    val validate: (String) -> Boolean = { true },
) {
    private var _textFieldValue: TextFieldValue by mutableStateOf(textFieldValue.copy())
    val textFieldValue: TextFieldValue get() = _textFieldValue.copy()
    val text: String get() = _textFieldValue.text

    var isError: Boolean by mutableStateOf(!validate(textFieldValue.text))
        private set

    constructor(
        text: String,
        onValueChange: (String) -> Unit = {},
        placeholder: String? = null,
        maxLength: Int? = null,
        validate: (String) -> Boolean = { true },
    ) : this(
        textFieldValue = TextFieldValue(text),
        onValueChange = onValueChange,
        placeholder = placeholder,
        maxLength = maxLength,
        validate = validate,
    )

    fun updateText(newTextFieldValue: TextFieldValue) {
        if (hasExceededMaxLength(newTextFieldValue.text)) {
            return
        }
        _textFieldValue = newTextFieldValue.copy()
        onValueChange(newTextFieldValue.text)
        isError = !validate(newTextFieldValue.text)
    }

    private fun hasExceededMaxLength(text: String): Boolean {
        if (maxLength == null) {
            return false
        }
        return text.length > maxLength
    }

    companion object {
        fun saver(
            onValueChange: (String) -> Unit,
            validate: (String) -> Boolean,
        ): Saver<GgzzTextFieldState, *> =
            Saver(
                save = {
                    listOf(
                        it.textFieldValue.text,
                        it.textFieldValue.selection.start,
                        it.textFieldValue.selection.end,
                        it.placeholder,
                        it.maxLength,
                    )
                },
                restore = {
                    GgzzTextFieldState(
                        TextFieldValue(
                            text = it[0] as String,
                            selection = TextRange(it[1] as Int, it[2] as Int),
                        ),
                        placeholder = it[3] as String?,
                        maxLength = it[5] as Int?,
                        onValueChange = onValueChange,
                        validate = validate,
                    )
                },
            )
    }
}

@Composable
internal fun rememberSaveableGgzzTextFieldState(
    text: String = "",
    onValueChange: (String) -> Unit = {},
    placeholder: String? = null,
    maxLength: Int? = null,
    validate: (String) -> Boolean = { true },
): GgzzTextFieldState {
    return rememberSaveable(saver = GgzzTextFieldState.saver(onValueChange, validate)) {
        GgzzTextFieldState(
            text = text,
            onValueChange = onValueChange,
            placeholder = placeholder,
            maxLength = maxLength,
            validate = validate,
        )
    }
}
