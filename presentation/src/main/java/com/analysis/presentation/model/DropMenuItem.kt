package com.analysis.presentation.model

data class DropMenuItem(
    val label: String,
    val onClick: () -> Unit
)
