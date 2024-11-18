package com.netimur.buckshooter.domain.di

import com.netimur.buckshooter.data.repository.ShellsRepository
import com.netimur.buckshooter.domain.usecases.LoadShellsUseCase
import com.netimur.buckshooter.domain.usecases.ShootBlankShellUseCase
import com.netimur.buckshooter.domain.usecases.ShootLiveShellUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ShellsUseCasesModule {
    @Provides
    fun provideLoadShellsUseCase(shellsRepository: ShellsRepository): LoadShellsUseCase {
        return LoadShellsUseCase(shellsRepository = shellsRepository)
    }

    @Provides
    fun provideShootBlankShellUseCase(shellsRepository: ShellsRepository): ShootBlankShellUseCase {
        return ShootBlankShellUseCase(shellsRepository = shellsRepository)
    }

    @Provides
    fun provideLiveBlankShellUseCase(shellsRepository: ShellsRepository): ShootLiveShellUseCase {
        return ShootLiveShellUseCase(shellsRepository = shellsRepository)
    }
}