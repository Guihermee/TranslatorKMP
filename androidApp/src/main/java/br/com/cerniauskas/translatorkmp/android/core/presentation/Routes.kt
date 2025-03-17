package br.com.cerniauskas.translatorkmp.core.presentation

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object Translate: Routes

    @Serializable
    data class VoiceToText(
        val languageCode: String
    ): Routes
}