package com.netimur.buckshooter.core.storage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shells")
data class ShellEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "shell_type")
    val shellType: String,
    @ColumnInfo(name = "shell_ordinal_number")
    val number: Int?
)