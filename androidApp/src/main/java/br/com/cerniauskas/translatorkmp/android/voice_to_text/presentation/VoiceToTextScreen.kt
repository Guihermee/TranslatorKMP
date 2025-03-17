package br.com.cerniauskas.translatorkmp.android.voice_to_text.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.cerniauskas.translatorkmp.voice_to_text.presentation.VoiceToTextAction

@Composable
fun VoiceToTextScreen(
    languageCode: String,
    modifier: Modifier = Modifier,
    onResult: (String) -> Unit,
    onAction: (VoiceToTextAction) -> Unit
) {
    Text("Voice to text! Language code is: $languageCode")
}