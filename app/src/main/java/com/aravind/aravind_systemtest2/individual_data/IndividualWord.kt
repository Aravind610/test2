package com.aravind.aravind_systemtest2.individual_data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class individualWord(
        @PrimaryKey @ColumnInfo(name = "word") val word: String)
