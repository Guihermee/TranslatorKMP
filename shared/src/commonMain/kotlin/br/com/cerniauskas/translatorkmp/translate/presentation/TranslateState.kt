package br.com.cerniauskas.translatorkmp.translate.presentation

import br.com.cerniauskas.translatorkmp.core.domain.DataError
import br.com.cerniauskas.translatorkmp.core.presentation.UiLanguage

data class TranslateState(
    val fromText: String = "",
    val toText: String? = null,
    val isTranslating: Boolean = false,
    val fromLanguage: UiLanguage = UiLanguage.byCode("en"),
    val toLanguage: UiLanguage = UiLanguage.byCode("pt"),
    val isChoosingFromLanguage: Boolean = false,
    val isChoosingToLanguage: Boolean = false,
    val error: DataError? = null,
    val history: List<UiHistoryItem> = emptyList()
)
