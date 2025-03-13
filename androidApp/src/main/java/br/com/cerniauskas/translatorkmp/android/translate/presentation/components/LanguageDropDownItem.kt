package br.com.cerniauskas.translatorkmp.translate.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.cerniauskas.translatorkmp.core.presentation.UiLanguage

@Composable
fun LanguageDropDownItem(
    language: UiLanguage,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenuItem(
        onClick = onClick,
        modifier =  modifier,
        text =  {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = language.drawableRes),
                    contentDescription = language.language.langName,
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = language.language.langName)
            }
        }
    )

}