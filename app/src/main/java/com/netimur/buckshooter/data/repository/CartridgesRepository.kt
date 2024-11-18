package com.netimur.buckshooter.data.repository

import com.netimur.buckshooter.data.model.Shell
import kotlinx.coroutines.flow.Flow

interface ShellsRepository {
    suspend fun addShell(shell: Shell)
    suspend fun addShells(shells: List<Shell>)
    suspend fun removeBlankShell()
    suspend fun removeLiveShell()
    fun observeShells(): Flow<List<Shell>>
    suspend fun clearShells()
}