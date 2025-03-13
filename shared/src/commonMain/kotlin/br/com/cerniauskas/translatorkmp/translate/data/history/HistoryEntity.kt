package br.com.cerniauskas.translatorkmp.translate.data.history

data class HistoryEntity (
    val id: Long?,
    val fromLanguageCode: String,
    val fromText: String,
    val toLanguageCode: String,
    val toText: String,
    val timestamp: Long
)