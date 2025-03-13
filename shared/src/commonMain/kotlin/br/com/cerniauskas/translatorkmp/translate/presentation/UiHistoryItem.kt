package br.com.cerniauskas.translatorkmp.translate.presentation

import br.com.cerniauskas.translatorkmp.core.presentation.UiLanguage

data class UiHistoryItem(
    val id: Long,
    val fromText: String,
    val toText: String,
    val fromLanguage: UiLanguage,
    val toLanguage: UiLanguage
)
