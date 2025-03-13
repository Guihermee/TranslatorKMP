package br.com.cerniauskas.translatorkmp.translate.domain

data class HistoryItem(
    val id: Int?,
    val fromLanguageCode: String,
    val fromText: String,
    val toLanguageCode: String,
    val toText: String
)
