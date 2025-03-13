package br.com.cerniauskas.translatorkmp.core.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null, extraModule: Module? = null) {
    startKoin {
        config?.invoke(this)
        modules(listOfNotNull(sharedModules, platformModule, extraModule))
    }
}