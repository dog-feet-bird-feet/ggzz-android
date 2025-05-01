package com.analysis.domain.model

import java.time.LocalDateTime

data class VerificationResult(
    val id:String,
    val title:String,
    val similarity:Double,
    val pressure:Double,
    val inclination:Double,
    val verificationImgUrl: String,
    val createdAt: LocalDateTime,
)
