package br.com.cerniauskas.translatorkmp.translate.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.cerniauskas.translatorkmp.R
import br.com.cerniauskas.translatorkmp.core.presentation.UiLanguage
import br.com.cerniauskas.translatorkmp.core.theme.LightBlue
import coil3.compose.AsyncImage

@Composable
fun LanguageDropDown(
    language: UiLanguage,
    isOpen: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onSelectLanguage: (UiLanguage) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        DropdownMenu(
            expanded = isOpen,
            onDismissRequest = onDismiss
        ) {
            UiLanguage.allLanguages.forEach { language ->
                LanguageDropDownItem(
                    language = language,
                    onClick = {
                        onSelectLanguage(language)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage( // Usando AsyncImage do Coil por que tem um bug quando muda a imagem ela não aparece.
                model = language.drawableRes,
                contentDescription = language.language.langName,
                modifier = Modifier.size(25.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = language.language.langName,
                color = LightBlue,
                fontSize = 12.sp
            )
            Icon(
                imageVector = if (isOpen) Icons.Filled.ArrowDropUp else Icons.Default.ArrowDropDown,
                contentDescription = if (isOpen) {
                    stringResource(R.string.close)
                } else {
                    stringResource(R.string.open)
                },
                tint = LightBlue,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}