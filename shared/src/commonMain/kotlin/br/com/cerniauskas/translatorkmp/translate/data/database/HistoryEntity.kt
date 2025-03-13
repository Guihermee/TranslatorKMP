package br.com.cerniauskas.translatorkmp.translate.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val fromLanguageCode: String,
    val fromText: String,
    val toLanguageCode: String,
    val toText: String,
    val timestamp: Long
)