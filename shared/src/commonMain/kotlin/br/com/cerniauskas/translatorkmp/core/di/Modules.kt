package br.com.cerniauskas.translatorkmp.core.di


import br.com.cerniauskas.bookpedia.core.data.HttpClientFactory
import br.com.cerniauskas.translatorkmp.database.TranslateDatabase
import br.com.cerniauskas.translatorkmp.translate.data.history.SqlDelightHistoryDataSource
import br.com.cerniauskas.translatorkmp.translate.data.network.KtorRemoteTranslateClient
import br.com.cerniauskas.translatorkmp.translate.domain.HistoryDataSource
import br.com.cerniauskas.translatorkmp.translate.domain.RemoteTranslateClient
import br.com.cerniauskas.translatorkmp.translate.domain.usercase.Translate
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModules = module {

    // Ktor
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteTranslateClient).bind<RemoteTranslateClient>()

    // Database
//    single {
//        get<DatabaseFactory>()
//            .create()
//            .setDriver(BundledSQLiteDriver())
//            .build()
//    }
//    single { get<HistoryDatabase>().historyDao }

    // Repository/Data Source
    singleOf(::SqlDelightHistoryDataSource).bind<HistoryDataSource>()
    single { TranslateDatabase(get()) } // TranslateDatabase

    // User Cases
    singleOf(::Translate)
}