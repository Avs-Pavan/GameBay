package com.pavan.gamebay.core.presentaion.designsystem.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pavan.gamebay.core.presentaion.designsystem.R

// Set of Material typography styles to start with

val OswaldFontFamily = FontFamily(
    Font(R.font.oswald_light, FontWeight.Light),
    Font(R.font.oswald_regular, FontWeight.Normal),
    Font(R.font.oswald_medium, FontWeight.Medium),
    Font(R.font.oswald_bold, FontWeight.Bold)
)


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = OswaldFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.5.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = OswaldFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = OswaldFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.25.sp
    ),
    titleLarge = TextStyle(
        fontFamily = OswaldFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp,
        lineHeight = 48.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontFamily = OswaldFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.25.sp
    ),
)

/**
 * Extension function to set the font weight of a TextStyle to Medium.
 */
fun TextStyle.regular() = copy(fontWeight = FontWeight.Medium)

/**
 * Extension function to set the font weight of a TextStyle to Bold.
 */
fun TextStyle.bold() = copy(fontWeight = FontWeight.Bold)

/**
 * Extension function to set the font weight of a TextStyle to Thin.
 */
fun TextStyle.thin() = copy(fontWeight = FontWeight.Thin)
