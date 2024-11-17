package com.netimur.buckshooter.core.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.netimur.buckshooter.core.storage.model.CartridgeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartridgesDao {
    @Insert(entity = CartridgeEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCartridge(cartridge: CartridgeEntity)

    @Insert(entity = CartridgeEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCartridges(cartridges: List<CartridgeEntity>)

    @Query(value = "DELETE FROM cartridges WHERE cartridge_type='BLANK' LIMIT 1")
    suspend fun removeBlankCartridge()

    @Query(value = "DELETE FROM cartridges WHERE cartridge_type='COMBAT' LIMIT 1")
    suspend fun removeCombatCartridge()

    @Query(value = "SELECT * FROM cartridges")
    fun observeCartridges(): Flow<List<CartridgeEntity>>

    @Delete(entity = CartridgeEntity::class)
    suspend fun clearCartridges()
}