package com.analysis.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.analysis.presentation.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun LocalDateTime?.toFormattedString(): String {
    if (this == null) return ""
    val formatPattern = stringResource(id = R.string.datetime)
    val formatter = DateTimeFormatter.ofPattern(formatPattern)
    return this.format(formatter)
}
