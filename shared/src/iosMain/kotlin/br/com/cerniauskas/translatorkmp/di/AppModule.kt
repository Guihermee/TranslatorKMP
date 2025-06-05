package br.com.cerniauskas.translatorkmp.di

import br.com.cerniauskas.translatorkmp.core.data.HttpClientFactory
import br.com.cerniauskas.translatorkmp.database.TranslateDatabase
import br.com.cerniauskas.translatorkmp.translate.data.history.SqlDelightHistoryDataSource
import br.com.cerniauskas.translatorkmp.translate.data.local.DatabaseDriverFactory
import br.com.cerniauskas.translatorkmp.translate.data.network.KtorRemoteTranslateClient
import br.com.cerniauskas.translatorkmp.translate.domain.HistoryDataSource
import br.com.cerniauskas.translatorkmp.translate.domain.RemoteTranslateClient
import br.com.cerniauskas.translatorkmp.translate.domain.usercase.Translate
import io.ktor.client.engine.darwin.Darwin

interface AppModule {
    val historyDataSource: HistoryDataSource
    val client: RemoteTranslateClient
    val translateUseCase: Translate
}

class AppModuleImpl: AppModule {

    override val client: RemoteTranslateClient by lazy {
        KtorRemoteTranslateClient(
            HttpClientFactory.create(Darwin.create())
        )
    }

    override val historyDataSource: HistoryDataSource by lazy {
        SqlDelightHistoryDataSource(
            TranslateDatabase(
                DatabaseDriverFactory().create()
            )
        )
    }

    override val translateUseCase: Translate by lazy {
        Translate(client, historyDataSource)
    }

}
