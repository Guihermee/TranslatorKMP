package br.com.cerniauskas.translatorkmp.translate.data.database

//import androidx.room.Dao
//import androidx.room.Query
//import androidx.room.Upsert
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface HistoryDao {
//
//    @Upsert
//    suspend fun upsert(historyEntity: HistoryEntity)
//
//    @Query("SELECT * FROM historyentity order by timestamp DESC")
//    fun getHistory(): Flow<List<HistoryEntity>>
//
//    @Query("DELETE FROM historyentity WHERE id = :id")
//    suspend fun deleteFromHistoryById(id: Int)
//
//}