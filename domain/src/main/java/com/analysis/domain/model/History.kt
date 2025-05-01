package com.analysis.domain.model

import java.time.LocalDateTime

data class History(
    val id: String,
    val title: String,
    val createdAt: LocalDateTime,
    val verificationImgUrl: String,
)
