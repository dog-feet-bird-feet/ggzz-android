package com.analysis.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonalityResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("traits")
    val traits: Traits,

    @SerialName("type")
    val type: String,

    @SerialName("description")
    val description: String
)

@Serializable
data class Traits(
    @SerialName("size")
    val size: TraitDetail,

    @SerialName("pressure")
    val pressure: TraitDetail,

    @SerialName("inclination")
    val inclination: TraitDetail,

    @SerialName("shape")
    val shape: TraitDetail
)

@Serializable
data class TraitDetail(
    @SerialName("score")
    val score: String,

    @SerialName("detail")
    val detail: String
)
