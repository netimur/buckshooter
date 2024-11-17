package com.netimur.buckshooter.data.di

import com.netimur.buckshooter.core.storage.dao.CartridgesDao
import com.netimur.buckshooter.data.repository.CartridgesRepository
import com.netimur.buckshooter.data.repository.CartridgesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CartridgesModule {
    @Provides
    fun provideCartridgesRepository(cartridgesDao: CartridgesDao): CartridgesRepository {
        return CartridgesRepositoryImpl(cartridgesDao = cartridgesDao)
    }
}