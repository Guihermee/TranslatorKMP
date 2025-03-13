package br.com.cerniauskas.translatorkmp.translate.data.network

import br.com.cerniauskas.translatorkmp.core.data.safeCall
import br.com.cerniauskas.translatorkmp.core.domain.DataError
import br.com.cerniauskas.translatorkmp.core.domain.Result
import br.com.cerniauskas.translatorkmp.core.domain.language.Language
import br.com.cerniauskas.translatorkmp.translate.data.dto.TranslateDto
import br.com.cerniauskas.translatorkmp.translate.data.dto.TranslatedDto
import br.com.cerniauskas.translatorkmp.translate.domain.RemoteTranslateClient
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

private const val BASE_URL = "https://translate.pl-coding.com"

class KtorRemoteTranslateClient(
    private val httpClient: HttpClient
): RemoteTranslateClient {


    override suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): Result<TranslatedDto, DataError.Remote> {
        return safeCall<TranslatedDto> {
            httpClient.post {
                url("$BASE_URL/translate")
                contentType(ContentType.Application.Json)
                setBody(
                    TranslateDto(
                        textToTranslate = fromText,
                        sourceLanguageCode = fromLanguage.langCode,
                        targetLanguageCode = toLanguage.langCode
                    )
                )
            }
        }
    }

}