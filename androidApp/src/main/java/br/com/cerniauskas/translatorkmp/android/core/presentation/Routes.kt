package br.com.cerniauskas.translatorkmp.android.core.presentation

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object Translate: Routes

    @Serializable
    data class VoiceToText(
        val languageCode: String
    ): Routes
}