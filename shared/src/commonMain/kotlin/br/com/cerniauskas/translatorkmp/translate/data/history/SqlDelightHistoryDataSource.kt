package br.com.cerniauskas.translatorkmp.translate.data.history

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import br.com.cerniauskas.translatorkmp.core.domain.util.CommonFlow
import br.com.cerniauskas.translatorkmp.core.domain.util.toCommonFlow
import br.com.cerniauskas.translatorkmp.database.TranslateDatabase
import br.com.cerniauskas.translatorkmp.translate.domain.HistoryDataSource
import br.com.cerniauskas.translatorkmp.translate.domain.HistoryItem
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlin.coroutines.CoroutineContext

class SqlDelightHistoryDataSource(
    db: TranslateDatabase
) : HistoryDataSource {

    private val queries = db.translateQueries

    override fun getHistory(context: CoroutineContext): CommonFlow<List<HistoryItem>> {
        return queries
            .getHistory()
            .asFlow()
            .mapToList(context)
            .map { historyEntities ->
                historyEntities.map { historyEntity ->
                    HistoryItem(
                        id = historyEntity.id,
                        fromLanguageCode = historyEntity.fromLanguageCode,
                        fromText = historyEntity.fromText,
                        toLanguageCode = historyEntity.toLanguageCode,
                        toText = historyEntity.toText
                    )
                }
            }
            .toCommonFlow()
    }

    override suspend fun insertHistoryItem(item: HistoryItem) {
        queries.insertHistoryEntity(
            id = item.id,
            fromLanguageCode = item.fromLanguageCode,
            fromText = item.fromText,
            toLanguageCode = item.toLanguageCode,
            toText = item.toText,
            timestamp = Clock.System.now().toEpochMilliseconds()
        )
    }
}

