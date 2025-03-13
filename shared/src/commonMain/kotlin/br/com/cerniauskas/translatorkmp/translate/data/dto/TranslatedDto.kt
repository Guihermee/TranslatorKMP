package br.com.cerniauskas.translatorkmp.translate.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TranslatedDto(
    val translatedText: String
)
