package com.netimur.buckshooter.core.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.netimur.buckshooter.core.storage.dao.ShellsDao
import com.netimur.buckshooter.core.storage.model.ShellEntity

@Database(entities = [ShellEntity::class], version = 1)
abstract class BuckshooterDatabase : RoomDatabase() {
    abstract fun ShellsDao(): ShellsDao
}