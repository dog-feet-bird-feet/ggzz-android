package com.analysis.data.remote.dto.response

import com.analysis.domain.model.History
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HistoryResponse(
    @SerialName("id") val id: String,
    @SerialName("name") val title: String,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("verificationImgUrl") val verificationImgUrl: String,
)

fun HistoryResponse.toHistory() =
    History(
        id = this.id,
        title = this.title,
        createdAt = this.createdAt,
        verificationImgUrl = this.verificationImgUrl,
    )
