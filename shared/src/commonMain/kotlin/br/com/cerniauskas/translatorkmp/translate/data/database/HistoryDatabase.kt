package br.com.cerniauskas.translatorkmp.translate.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [HistoryEntity::class],
    version = 1
)
@ConstructedBy(HistoryDatabaseConstructor::class)
abstract class HistoryDatabase: RoomDatabase() {
    abstract val historyDao: HistoryDao

    companion object {
        const val DATABASE_NAME = "translate_db"
    }

}