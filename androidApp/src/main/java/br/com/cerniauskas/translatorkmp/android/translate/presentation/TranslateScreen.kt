package br.com.cerniauskas.translatorkmp.android.translate.presentation

import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import br.com.cerniauskas.translatorkmp.R
import br.com.cerniauskas.translatorkmp.core.domain.DataError
import br.com.cerniauskas.translatorkmp.translate.presentation.TranslateAction
import br.com.cerniauskas.translatorkmp.translate.presentation.TranslateState
import br.com.cerniauskas.translatorkmp.translate.presentation.components.LanguageDropDown
import br.com.cerniauskas.translatorkmp.translate.presentation.components.SwapLanguagesButton
import br.com.cerniauskas.translatorkmp.translate.presentation.components.TranslateHistoryItem
import br.com.cerniauskas.translatorkmp.translate.presentation.components.TranslateTextField
import br.com.cerniauskas.translatorkmp.translate.presentation.components.rememberTextToSpeech
import java.util.Locale

@Composable
fun TranslateScreen(
    state: TranslateState,
    onAction: (TranslateAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    LaunchedEffect(state.error) {
        val message = when (state.error) {
            DataError.Local.DISK_FULL -> context.getString(R.string.error_service_unavailable)
            DataError.Local.UNKNOWN -> context.getString(R.string.error_service_unavailable)
            DataError.Remote.REQUEST_TIMEOUT -> context.getString(R.string.error_service_unavailable)
            DataError.Remote.TOO_MANY_REQUESTS -> context.getString(R.string.error_service_unavailable)
            DataError.Remote.NO_INTERNET -> context.getString(R.string.error_service_unavailable)
            DataError.Remote.SERVER -> context.getString(R.string.server_error)
            DataError.Remote.SERIALIZATION -> context.getString(R.string.error_service_unavailable)
            DataError.Remote.UNKNOWN -> context.getString(R.string.error_service_unavailable)
            null -> context.getString(R.string.error_service_unavailable)
        }
        //
    }

    Scaffold(
        floatingActionButton = {

        }
    ){ padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LanguageDropDown(
                        language = state.fromLanguage,
                        isOpen = state.isChoosingFromLanguage,
                        onClick = { onAction(TranslateAction.OpenFromLanguageDropDown) },
                        onDismiss = { onAction(TranslateAction.StopChoosingLanguage) },
                        onSelectLanguage = { onAction(TranslateAction.ChooseFromLanguage(it)) }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    SwapLanguagesButton(
                        onClick = { onAction(TranslateAction.SwapLanguages) }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    LanguageDropDown(
                        language = state.toLanguage,
                        isOpen = state.isChoosingToLanguage,
                        onClick = { onAction(TranslateAction.OpenToLanguageDropDown) },
                        onDismiss = { onAction(TranslateAction.StopChoosingLanguage) },
                        onSelectLanguage = { onAction(TranslateAction.ChooseToLanguage(it)) }
                    )
                }
            }
            item {
                val clipboardManager = LocalClipboardManager.current
                val keyboardController = LocalSoftwareKeyboardController.current
                val tts = rememberTextToSpeech()
                TranslateTextField(
                    fromText = state.fromText,
                    toText = state.toText,
                    fromLanguage = state.fromLanguage,
                    toLanguage = state.toLanguage,
                    isTranslating = state.isTranslating,
                    onTranslateClick = {
                        keyboardController?.hide()
                        onAction(TranslateAction.Translate)
                    },
                    onTextChange = {
                        onAction(TranslateAction.ChangeTranslationText(it))
                    },
                    onCopyClick = { text ->
                        clipboardManager.setText(
                            buildAnnotatedString {
                                append(text)
                            }
                        )
                        Toast.makeText(
                            context,
                            context.getString(R.string.copied_to_clipboard),
                            Toast.LENGTH_LONG
                        ).show()

                    },
                    onCloseClick = {
                        onAction(TranslateAction.CloseTranslation)
                    },
                    onSpeakerClick = {
                        tts.language = state.toLanguage.toLocale() ?: Locale.ENGLISH
                        tts.speak(
                            state.toText,
                            TextToSpeech.QUEUE_FLUSH,
                            null,
                            null
                        )
                    },
                    onTextFieldClick = {
                        onAction(TranslateAction.EditTranslation)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                if (state.history.isNotEmpty()) {
                    Text(
                        text = stringResource(id = R.string.history),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
            items(state.history) { item ->
                TranslateHistoryItem(
                    item = item,
                    onClick = {
                        onAction(TranslateAction.SelectHistoryItem(item))
                    }
                )
            }
        }
    }
}