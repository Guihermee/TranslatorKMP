package br.com.cerniauskas.translatorkmp.core.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import br.com.cerniauskas.bookpedia.core.data.HttpClientFactory
import br.com.cerniauskas.translatorkmp.translate.data.database.DatabaseFactory
import br.com.cerniauskas.translatorkmp.translate.data.database.HistoryDatabase
import br.com.cerniauskas.translatorkmp.translate.data.history.RoomHistoryDataSource
import br.com.cerniauskas.translatorkmp.translate.data.network.KtorRemoteTranslateClient
import br.com.cerniauskas.translatorkmp.translate.domain.HistoryDataSource
import br.com.cerniauskas.translatorkmp.translate.domain.RemoteTranslateClient
import br.com.cerniauskas.translatorkmp.translate.domain.usercase.Translate
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModules = module {

    // Ktor
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteTranslateClient).bind<RemoteTranslateClient>()

    // Database
    single {
        get<DatabaseFactory>()
            .create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<HistoryDatabase>().historyDao }

    // Repository/Data Source
    singleOf(::RoomHistoryDataSource).bind<HistoryDataSource>()

    // User Cases
    singleOf(::Translate)
}