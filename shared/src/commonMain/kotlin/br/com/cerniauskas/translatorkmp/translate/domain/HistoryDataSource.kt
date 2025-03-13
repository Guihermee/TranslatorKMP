package br.com.cerniauskas.translatorkmp.translate.domain

import br.com.cerniauskas.translatorkmp.core.domain.util.CommonFlow
import kotlin.coroutines.CoroutineContext

interface HistoryDataSource {
    fun getHistory(context: CoroutineContext): CommonFlow<List<HistoryItem>>
    suspend fun insertHistoryItem(item: HistoryItem)
}