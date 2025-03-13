package br.com.cerniauskas.translatorkmp.core.presentation

import br.com.cerniauskas.translatorkmp.core.domain.language.Language

expect class UiLanguage{
    val language: Language
    companion object {
        fun byCode(code: String): UiLanguage
        val allLanguages: List<UiLanguage>
    }
}