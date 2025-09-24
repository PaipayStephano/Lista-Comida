package com.mexiti.listacomida.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mexiti.listacomida.R

val dmsan = FontFamily(
    Font(R.font.dmsans18ptbold),
    Font(R.font.dmsans18ptregular)
)

val darker = FontFamily(
    Font(R.font.darkergrotesqueregular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = darker,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 34.sp
    ),

    displayMedium = TextStyle(
        fontFamily = dmsan,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
    ),

    labelSmall = TextStyle(
        fontFamily = dmsan,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
    ),

    bodyMedium = TextStyle(
        fontFamily = dmsan,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
    )
)
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
