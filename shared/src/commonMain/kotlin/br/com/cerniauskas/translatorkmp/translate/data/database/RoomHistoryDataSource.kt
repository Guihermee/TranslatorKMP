package br.com.cerniauskas.translatorkmp.translate.data.database

//import br.com.cerniauskas.translatorkmp.core.domain.util.CommonFlow
//import br.com.cerniauskas.translatorkmp.core.domain.util.toCommonFlow
//import br.com.cerniauskas.translatorkmp.translate.data.database.HistoryDao
//import br.com.cerniauskas.translatorkmp.translate.data.history.toHistoryEntity
//import br.com.cerniauskas.translatorkmp.translate.domain.HistoryDataSource
//import br.com.cerniauskas.translatorkmp.translate.domain.HistoryItem
//import kotlinx.coroutines.flow.map
//
//class RoomHistoryDataSource(
//    private val historyDao: HistoryDao
//): HistoryDataSource {
//    override fun getHistory(): CommonFlow<List<HistoryItem>> {
//        return historyDao
//            .getHistory()
//            .map { history ->
//                history.map { historyEntity ->
//                    historyEntity.toHistoryItem()
//                }
//            }
//            .toCommonFlow()
//    }
//
//    override suspend fun insertHistoryItem(item: HistoryItem) {
//        historyDao.upsert(item.toHistoryEntity())
//    }
//}