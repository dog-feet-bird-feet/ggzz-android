package com.analysis.domain.model

data class Personality(
    val id: Int,
    val traits: Traits,
    val type: String,
    val typeDescription: String,
    val description: String,
)
