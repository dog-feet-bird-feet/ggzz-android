package com.analysis.domain.model

data class HistoryDetail(
    val id: String,
    val title: String,
    val similarity: Float,
    val pressure: Float,
    val inclination: Float,
    val verificationImgUrl: String,
    val createdAt: String,
)
