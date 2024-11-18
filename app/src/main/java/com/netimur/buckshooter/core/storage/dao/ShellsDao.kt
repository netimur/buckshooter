package com.netimur.buckshooter.core.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.netimur.buckshooter.core.storage.model.ShellEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShellsDao {
    @Insert(entity = ShellEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShell(shell: ShellEntity)

    @Insert(entity = ShellEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShells(shells: List<ShellEntity>)

    @Query(value = "SELECT * FROM shells WHERE Shell_type='BLANK' LIMIT 1")
    suspend fun firstBlankShellOrNull(): ShellEntity?

    @Query(value = "SELECT * FROM shells WHERE Shell_type='LIVE' LIMIT 1")
    suspend fun firstLiveShellOrNull(): ShellEntity?

    @Delete
    suspend fun removeShell(shellEntity: ShellEntity)

    @Query(value = "DELETE FROM Shells WHERE id = :shellId")
    suspend fun removeShellById(shellId: Int)

    @Query(value = "SELECT * FROM shells")
    fun observeShells(): Flow<List<ShellEntity>>

    @Query(value = "DELETE FROM shells")
    suspend fun clearShells()
}