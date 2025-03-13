package br.com.cerniauskas.translatorkmp.translate.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.cerniauskas.translatorkmp.core.presentation.UiLanguage
import br.com.cerniauskas.translatorkmp.core.theme.LightBlue

@Composable
fun LanguageDisplay(
    language: UiLanguage,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SmallLanguageIcon(language = language)
        Spacer(Modifier.width(8.dp))
        Text(
            text = language.language.langName,
            color = LightBlue
        )
    }
}