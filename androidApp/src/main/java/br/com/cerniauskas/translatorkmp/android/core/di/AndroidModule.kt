package br.com.cerniauskas.translatorkmp.core.di

import br.com.cerniauskas.translatorkmp.translate.presentation.AndroidTranslateViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val androidModule = module {
    viewModelOf(::AndroidTranslateViewModel)
}