package br.com.cerniauskas.translatorkmp.android.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import br.com.cerniauskas.translatorkmp.core.data.HttpClientFactory
import br.com.cerniauskas.translatorkmp.database.TranslateDatabase
import br.com.cerniauskas.translatorkmp.translate.data.history.SqlDelightHistoryDataSource
import br.com.cerniauskas.translatorkmp.translate.data.local.DatabaseDriverFactory
import br.com.cerniauskas.translatorkmp.translate.data.network.KtorRemoteTranslateClient
import br.com.cerniauskas.translatorkmp.translate.domain.HistoryDataSource
import br.com.cerniauskas.translatorkmp.translate.domain.RemoteTranslateClient
import br.com.cerniauskas.translatorkmp.translate.domain.usercase.Translate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory.create(OkHttp.create())
    }

    @Provides
    @Singleton
    fun provideTranslateClient(httpClient: HttpClient): RemoteTranslateClient {
        return KtorRemoteTranslateClient(httpClient)
    }

    @Provides
    @Singleton
    fun provideDatabaseDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).create()
    }

    @Provides
    @Singleton
    fun provideHistoryDataSource(driver: SqlDriver): HistoryDataSource {
        return SqlDelightHistoryDataSource(TranslateDatabase(driver))
    }

    @Provides
    @Singleton
    fun provideTranslateUseCase(
        client: RemoteTranslateClient,
        dataSource: HistoryDataSource
    ): Translate {
        return Translate(client, dataSource)
    }

}