package com.analysis.presentation.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.analysis.presentation.R

private val GgzzFontFamily =
    FontFamily(
        Font(R.font.pretendard_bold, FontWeight.Bold),
        Font(R.font.pretendard_medium, FontWeight.Medium),
        Font(R.font.pretendard_regular, FontWeight.Light),
    )

internal object GgzzTypography {
    val pretendardBold42 =
        TextStyle(
            fontFamily = GgzzFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 42.sp,
            lineHeight = 50.sp,
            color = Gray900,
        )

    val pretendardBold28 =
        TextStyle(
            fontFamily = GgzzFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 33.4.sp,
            color = Gray900,
        )

    val pretendardBold24 =
        TextStyle(
            fontFamily = GgzzFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 28.6.sp,
            color = Gray900,
        )

    val pretendardBold20 =
        TextStyle(
            fontFamily = GgzzFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 23.9.sp,
            color = Gray900,
        )

    val pretendardBold16 =
        TextStyle(
            fontFamily = GgzzFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 19.1.sp,
            color = Gray900,
        )

    val pretendardMedium24 =
        TextStyle(
            fontFamily = GgzzFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            lineHeight = 28.6.sp,
            color = Gray900,
        )

    val pretendardMedium20 =
        TextStyle(
            fontFamily = GgzzFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            lineHeight = 23.9.sp,
            color = Gray900,
        )

    val pretendardMedium18 =
        TextStyle(
            fontFamily = GgzzFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = 21.5.sp,
            color = Gray900,
        )

    val pretendardMedium16 =
        TextStyle(
            fontFamily = GgzzFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 19.1.sp,
            color = Gray900,
        )

    val pretendardMedium14 =
        TextStyle(
            fontFamily = GgzzFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 16.7.sp,
            color = Gray900,
        )

    val pretendardRegular24 =
        TextStyle(
            fontFamily = GgzzFontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 24.sp,
            lineHeight = 28.6.sp,
            color = Gray900,
        )

    val pretendardRegular16 =
        TextStyle(
            fontFamily = GgzzFontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            lineHeight = 19.1.sp,
            color = Gray900,
        )

    val pretendardRegular14 =
        TextStyle(
            fontFamily = GgzzFontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
            lineHeight = 16.7.sp,
            color = Gray900,
        )

    val pretendardRegular12 =
        TextStyle(
            fontFamily = GgzzFontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            lineHeight = 14.3.sp,
            color = Gray900,
        )

    val pretendardRegular10 =
        TextStyle(
            fontFamily = GgzzFontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 10.sp,
            lineHeight = 11.9.sp,
            color = Gray900,
        )

    val pretendardRegular8 =
        TextStyle(
            fontFamily = GgzzFontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 8.sp,
            lineHeight = 9.5.sp,
            color = Gray900,
        )
}
