package com.plcoding.cryptotracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.com.cerniauskas.translatorkmp.R


@Composable
fun appTypography(): Typography {
    val spaceMono = FontFamily(
        Font(
            resId = R.font.sf_pro_text_medium,
            weight = FontWeight.Medium
        ),
        Font(
            resId = R.font.sf_pro_text_regular,
            weight = FontWeight.Normal,
            style = FontStyle.Italic
        ),
        Font(
            resId = R.font.sf_pro_text_bold,
            weight = FontWeight.Bold
        )
    )

    return Typography(
        headlineLarge = TextStyle(
            fontFamily = spaceMono,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        ),
        headlineMedium =  TextStyle(
            fontFamily = spaceMono,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = spaceMono,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        ),
        bodySmall = TextStyle(
            fontFamily = spaceMono,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = spaceMono,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = spaceMono,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        labelMedium = TextStyle(
            fontFamily = spaceMono,
            fontWeight = FontWeight.Normal,
        )
    )
}
