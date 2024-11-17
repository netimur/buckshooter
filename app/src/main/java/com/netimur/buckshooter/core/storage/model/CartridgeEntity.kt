package com.netimur.buckshooter.core.storage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cartridges")
data class CartridgeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "cartridge_type")
    val cartridgeType: String,
    @ColumnInfo(name = "cartridge_ordinal_number")
    val number: Int?
)