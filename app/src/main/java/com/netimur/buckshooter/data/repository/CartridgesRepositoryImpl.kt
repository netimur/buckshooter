package com.netimur.buckshooter.data.repository

import com.netimur.buckshooter.core.foundation.utils.mapList
import com.netimur.buckshooter.core.storage.dao.CartridgesDao
import com.netimur.buckshooter.data.mappers.toDomain
import com.netimur.buckshooter.data.mappers.toEntity
import com.netimur.buckshooter.data.model.Cartridge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CartridgesRepositoryImpl(private val cartridgesDao: CartridgesDao) : CartridgesRepository {
    override suspend fun addCartridge(cartridge: Cartridge) {
        withContext(Dispatchers.IO) {
            cartridgesDao.addCartridge(cartridge = cartridge.toEntity())
        }
    }

    override suspend fun addCartridges(cartridges: List<Cartridge>) {
        withContext(Dispatchers.IO) {
            cartridgesDao.addCartridges(cartridges = cartridges.mapList { toEntity() })
        }
    }

    override suspend fun removeBlankCartridge() {
        withContext(Dispatchers.IO) {
            cartridgesDao.firstBlankCartridgeOrNull()?.let {
                cartridgesDao.removeCartridge(it)
            }
        }
    }

    override suspend fun removeCombatCartridge() {
        withContext(Dispatchers.IO) {
            cartridgesDao.firstCombatCartridgeOrNull()?.let {
                cartridgesDao.removeCartridgeById(it.id)
            }
        }
    }

    override fun observeCartridges(): Flow<List<Cartridge>> {
        return cartridgesDao.observeCartridges().map {
            it.mapList { toDomain() }
        }
    }

    override suspend fun clearCartridges() {
        withContext(Dispatchers.IO) {
            cartridgesDao.clearCartridges()
        }
    }
}