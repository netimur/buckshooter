package com.netimur.buckshooter.data.di

import com.netimur.buckshooter.core.storage.dao.ShellsDao
import com.netimur.buckshooter.data.repository.ShellsRepository
import com.netimur.buckshooter.data.repository.ShellsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ShellsModule {
    @Provides
    fun provideShellsRepository(shellsDao: ShellsDao): ShellsRepository {
        return ShellsRepositoryImpl(shellsDao = shellsDao)
    }
}