package com.netimur.buckshooter.core.storage.di

import android.content.Context
import androidx.room.Room
import com.netimur.buckshooter.core.storage.dao.ShellsDao
import com.netimur.buckshooter.core.storage.database.BuckshooterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RoomDatabaseModule {
    @Provides
    fun provideApplicationDatabase(@ApplicationContext context: Context): BuckshooterDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = BuckshooterDatabase::class.java,
            name = "application database"
        ).build()
    }

    @Provides
    fun provideShellsDao(database: BuckshooterDatabase): ShellsDao {
        return database.ShellsDao()
    }
}