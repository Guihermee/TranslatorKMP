package br.com.cerniauskas.translatorkmp.translate.domain.usercase

import br.com.cerniauskas.translatorkmp.core.domain.DataError
import br.com.cerniauskas.translatorkmp.core.domain.Result
import br.com.cerniauskas.translatorkmp.core.domain.language.Language
import br.com.cerniauskas.translatorkmp.core.domain.map
import br.com.cerniauskas.translatorkmp.core.domain.onSuccess
import br.com.cerniauskas.translatorkmp.translate.domain.HistoryDataSource
import br.com.cerniauskas.translatorkmp.translate.domain.HistoryItem
import br.com.cerniauskas.translatorkmp.translate.domain.RemoteTranslateClient

// Um repository não seria melhor? e tratar onSuccess no viewModel em vez do usercase?
class Translate(
    private val ktorRemoteTranslateClient: RemoteTranslateClient,
    private val roomHistoryDataSource: HistoryDataSource
) {
    suspend operator fun invoke(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): Result<String, DataError> {
        val result = ktorRemoteTranslateClient
            .translate(
                fromLanguage = fromLanguage,
                fromText = fromText,
                toLanguage = toLanguage
            )
            .map { it.translatedText }

        result
            .onSuccess {
                roomHistoryDataSource.insertHistoryItem(
                    HistoryItem(
                        id = null,
                        fromLanguageCode = fromLanguage.langCode,
                        fromText = fromText,
                        toLanguageCode = toLanguage.langCode,
                        toText = it
                    )
                )
            }

        return result
    }
}