package br.com.cerniauskas.translatorkmp.translate.data.history

import br.com.cerniauskas.translatorkmp.translate.data.database.HistoryEntity
import br.com.cerniauskas.translatorkmp.translate.domain.HistoryItem
import br.com.cerniauskas.translatorkmp.translate.presentation.UiHistoryItem
import kotlinx.datetime.Clock

fun HistoryEntity.toHistoryItem(): HistoryItem {
    return HistoryItem(
        id = id,
        fromLanguageCode = fromLanguageCode,
        fromText = fromText,
        toLanguageCode = toLanguageCode,
        toText = toText
    )
}

fun HistoryItem.toHistoryEntity(): HistoryEntity {
    return HistoryEntity(
        id = id,
        fromLanguageCode = fromLanguageCode,
        fromText = fromText,
        toLanguageCode = toLanguageCode,
        toText = toText,
        timestamp = Clock.System.now().toEpochMilliseconds()
    )
}