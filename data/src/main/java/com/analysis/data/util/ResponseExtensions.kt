package com.analysis.data.util

import org.json.JSONObject
import retrofit2.Response

fun Response<*>.errorMessage(): String {
    return try {
        JSONObject(errorBody()?.string() ?: "").getString("message")
    } catch (e: Exception) {
        "알 수 없는 오류입니다"
    }
}
