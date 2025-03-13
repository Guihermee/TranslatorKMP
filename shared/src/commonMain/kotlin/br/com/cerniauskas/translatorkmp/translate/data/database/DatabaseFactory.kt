package br.com.cerniauskas.translatorkmp.translate.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<HistoryDatabase>
}