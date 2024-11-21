package com.netimur.buckshooter.data.repository

import com.netimur.buckshooter.core.foundation.utils.mapList
import com.netimur.buckshooter.core.storage.dao.ShellsDao
import com.netimur.buckshooter.data.mappers.toDomain
import com.netimur.buckshooter.data.mappers.toEntity
import com.netimur.buckshooter.data.model.Shell
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ShellsRepositoryImpl(private val shellsDao: ShellsDao) : ShellsRepository {
    override suspend fun addShell(shell: Shell) {
        withContext(Dispatchers.IO) {
            shellsDao.addShell(shell = shell.toEntity())
        }
    }

    override suspend fun addShells(shells: List<Shell>) {
        withContext(Dispatchers.IO) {
            shellsDao.addShells(shells = shells.mapList { toEntity() })
        }
    }

    override suspend fun removeBlankShell() {
        withContext(Dispatchers.IO) {
            shellsDao.firstBlankShellOrNull()?.let {
                shellsDao.removeShell(it)
            }
        }
    }

    override suspend fun removeLiveShell() {
        withContext(Dispatchers.IO) {
            shellsDao.firstLiveShellOrNull()?.let {
                shellsDao.removeShellById(it.id)
            }
        }
    }

    override fun observeShells(): Flow<List<Shell>> {
        return shellsDao.observeShells().map {
            it.mapList { toDomain() }
        }
    }

    override suspend fun clearShells() {
        withContext(Dispatchers.IO) {
            shellsDao.clearShells()
        }
    }
}