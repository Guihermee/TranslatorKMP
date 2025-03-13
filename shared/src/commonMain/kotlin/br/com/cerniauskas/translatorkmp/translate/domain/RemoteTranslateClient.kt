package br.com.cerniauskas.translatorkmp.translate.domain

import br.com.cerniauskas.translatorkmp.core.domain.DataError
import br.com.cerniauskas.translatorkmp.core.domain.Result
import br.com.cerniauskas.translatorkmp.core.domain.language.Language
import br.com.cerniauskas.translatorkmp.translate.data.dto.TranslatedDto

interface RemoteTranslateClient {
    suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): Result<TranslatedDto, DataError.Remote>
}