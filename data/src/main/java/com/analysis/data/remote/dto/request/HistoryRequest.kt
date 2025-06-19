package com.analysis.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HistoryRequest(
    @SerialName("newName") val newTitle: String,
)
