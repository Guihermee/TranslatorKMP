package br.com.cerniauskas.translatorkmp.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.plcoding.cryptotracker.ui.theme.appTypography

@Composable
fun TranslatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors
    } else {
        lightColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = appTypography(),
        content = content
    )
}