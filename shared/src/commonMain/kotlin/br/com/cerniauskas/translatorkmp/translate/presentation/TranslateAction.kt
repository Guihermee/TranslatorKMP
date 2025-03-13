package br.com.cerniauskas.translatorkmp.translate.presentation

import androidx.compose.ui.text.TtsAnnotation
import br.com.cerniauskas.translatorkmp.core.presentation.UiLanguage

sealed class TranslateAction {
    data class ChooseFromLanguage(val language: UiLanguage): TranslateAction()
    data class ChooseToLanguage(val language: UiLanguage): TranslateAction()
    data object StopChoosingLanguage: TranslateAction()
    data object SwapLanguages: TranslateAction()
    data class ChangeTranslationText(val text: String): TranslateAction()
    data object Translate: TranslateAction()
    data object OpenFromLanguageDropDown: TranslateAction()
    data object OpenToLanguageDropDown: TranslateAction()
    data object CloseTranslation: TranslateAction()
    data class SelectHistoryItem(val item: UiHistoryItem): TranslateAction()
    data object EditTranslation: TranslateAction()
    data object RecordAudio: TranslateAction()
    data class SubmitVoiceResult(val result: String?): TranslateAction()
    data object OnErrorSeen: TranslateAction()
}