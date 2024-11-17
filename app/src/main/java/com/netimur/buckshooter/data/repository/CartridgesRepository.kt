package com.netimur.buckshooter.data.repository

import com.netimur.buckshooter.data.model.Cartridge
import kotlinx.coroutines.flow.Flow

interface CartridgesRepository {
    suspend fun addCartridge(cartridge: Cartridge)
    suspend fun addCartridges(cartridges: List<Cartridge>)
    suspend fun removeBlankCartridge()
    suspend fun removeCombatCartridge()
    fun observeCartridges(): Flow<List<Cartridge>>
    suspend fun clearCartridges()
}