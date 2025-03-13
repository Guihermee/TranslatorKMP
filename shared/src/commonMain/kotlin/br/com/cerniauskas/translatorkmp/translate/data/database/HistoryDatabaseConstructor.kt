package br.com.cerniauskas.translatorkmp.translate.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object HistoryDatabaseConstructor: RoomDatabaseConstructor<HistoryDatabase> {
    override fun initialize(): HistoryDatabase
}