package com.netimur.buckshooter.core.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.netimur.buckshooter.core.storage.dao.CartridgesDao
import com.netimur.buckshooter.core.storage.model.CartridgeEntity

@Database(entities = [CartridgeEntity::class], version = 1)
abstract class BuckshooterDatabase : RoomDatabase() {
    abstract fun cartridgesDao(): CartridgesDao
}