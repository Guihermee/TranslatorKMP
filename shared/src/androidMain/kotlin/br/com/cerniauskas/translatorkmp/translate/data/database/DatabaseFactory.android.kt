package br.com.cerniauskas.translatorkmp.translate.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<HistoryDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(HistoryDatabase.DATABASE_NAME)

        return Room.databaseBuilder(
            context = context ,
            name = dbFile.absolutePath
        )
    }
}