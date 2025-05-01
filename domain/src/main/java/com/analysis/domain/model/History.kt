package com.analysis.domain.model

import java.time.LocalDateTime

data class History(
    val id: String,
    val name: String,
    val createdAt: LocalDateTime?,
    val verificationImgUrl: String,
)
