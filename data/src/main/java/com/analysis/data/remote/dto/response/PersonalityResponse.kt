package com.analysis.data.remote.dto.response

import com.analysis.domain.model.Personality
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
    val description: String,
) {
    @Serializable
    data class Traits(
        @SerialName("size")
        val size: TraitDetail,

        @SerialName("pressure")
        val pressure: TraitDetail,

        @SerialName("inclination")
        val inclination: TraitDetail,

        @SerialName("shape")
        val shape: TraitDetail,
    )

    @Serializable
    data class TraitDetail(
        @SerialName("score")
        val score: String,

        @SerialName("detail")
        val detail: String,
    )

    fun Traits.toDomainTraits() = com.analysis.domain.model.Traits(
        this.size.toDomainTraitDetail(),
        this.pressure.toDomainTraitDetail(),
        this.inclination.toDomainTraitDetail(),
        this.shape.toDomainTraitDetail(),
    )

    private fun TraitDetail.toDomainTraitDetail() = com.analysis.domain.model.TraitDetail(
        this.score, this.detail
    )
}

fun PersonalityResponse.toPersonality() = Personality(
    id = this.id,
    traits = this.traits.toDomainTraits(),
    type = this.type,
    description = this.description
)
