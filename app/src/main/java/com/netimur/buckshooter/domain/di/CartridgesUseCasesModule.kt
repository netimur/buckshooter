package com.netimur.buckshooter.domain.di

import com.netimur.buckshooter.data.repository.CartridgesRepository
import com.netimur.buckshooter.domain.usecases.LoadCartridgesUseCase
import com.netimur.buckshooter.domain.usecases.ObserveBlankCartridgesUseCase
import com.netimur.buckshooter.domain.usecases.ObserveCombatCartridgesUseCase
import com.netimur.buckshooter.domain.usecases.ShootBlankCartridgeUseCase
import com.netimur.buckshooter.domain.usecases.ShootCombatCartridgeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CartridgesUseCasesModule {
    @Provides
    fun provideLoadCartridgesUseCase(cartridgesRepository: CartridgesRepository): LoadCartridgesUseCase {
        return LoadCartridgesUseCase(cartridgesRepository = cartridgesRepository)
    }

    @Provides
    fun provideShootBlankCartridgeUseCase(cartridgesRepository: CartridgesRepository): ShootBlankCartridgeUseCase {
        return ShootBlankCartridgeUseCase(cartridgesRepository = cartridgesRepository)
    }

    @Provides
    fun provideCombatBlankCartridgeUseCase(cartridgesRepository: CartridgesRepository): ShootCombatCartridgeUseCase {
        return ShootCombatCartridgeUseCase(cartridgesRepository = cartridgesRepository)
    }

    @Provides
    fun provideObserveCombatCartridgeUseCase(cartridgesRepository: CartridgesRepository): ObserveCombatCartridgesUseCase {
        return ObserveCombatCartridgesUseCase(cartridgesRepository = cartridgesRepository)
    }

    @Provides
    fun provideObserveBlankCartridgeUseCase(cartridgesRepository: CartridgesRepository): ObserveBlankCartridgesUseCase {
        return ObserveBlankCartridgesUseCase(cartridgesRepository = cartridgesRepository)
    }
}