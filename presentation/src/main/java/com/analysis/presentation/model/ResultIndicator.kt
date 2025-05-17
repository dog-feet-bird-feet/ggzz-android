package com.analysis.presentation.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.analysis.presentation.R
import com.analysis.presentation.theme.Green400
import com.analysis.presentation.theme.Red400
import com.analysis.presentation.theme.Yellow400

sealed class ResultIndicator(
    @StringRes val title: Int,
    val percentage: Float,
    val description: String,
    val progressColor: Color,
) {
    class Similarity(score: Float) : ResultIndicator(
        title = R.string.history_detail_similarity,
        percentage = score,
        description = when {
            score >= 0.8 -> "두 필기체의 전체적인 형태가 유사합니다."
            score >= 0.5 -> "두 필기체에 일부 유사한 특징이 있습니다."
            else -> "두 필기체의 형태 차이가 큽니다."
        },
        progressColor = Green400,
    )

    class Pressure(score: Float) : ResultIndicator(
        title = R.string.history_detail_pressure,
        percentage = score,
        description = when {
            score >= 0.8 -> "두 필기체의 필압 강도가 유사합니다."
            score >= 0.5 -> "두 필기체의 필압 경향이 일부 비슷합니다."
            else -> "두 필기체의 필압에 차이가 있습니다."
        },
        progressColor = Red400,
    )

    class Inclination(score: Float) : ResultIndicator(
        title = R.string.history_detail_inclination,
        percentage = score,
        description = when {
            score >= 0.8 -> "두 필기체의 기울기 방향이 유사합니다."
            score >= 0.5 -> "두 필기체의 기울기 패턴이 일부 비슷합니다."
            else -> "두 필기체의 기울기 차이가 큽니다."
        },
        progressColor = Yellow400,
    )
}
