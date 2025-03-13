package br.com.cerniauskas.translatorkmp.translate.domain

import br.com.cerniauskas.translatorkmp.core.domain.util.CommonFlow

interface HistoryDataSource {
    fun getHistory(): CommonFlow<List<HistoryItem>>
    suspend fun insertHistoryItem(item: HistoryItem)
}