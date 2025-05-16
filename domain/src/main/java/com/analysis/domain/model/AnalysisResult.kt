package com.analysis.domain.model

data class AnalysisResult(
    val similarity: Float,
    val pressure: Float,
    val inclination: Float,
    val verificationImgUrl: String,
    val createdAt: String,
)
