package br.com.cerniauskas.translatorkmp.translate.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.cerniauskas.translatorkmp.core.presentation.UiLanguage
import coil3.compose.AsyncImage

@Composable
fun SmallLanguageIcon(
    modifier: Modifier = Modifier,
    language: UiLanguage
) {
    AsyncImage(
        modifier = modifier.size(25.dp),
        model = language.drawableRes,
        contentDescription = language.language.langName,
    )
}