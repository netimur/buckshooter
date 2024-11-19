package com.netimur.buckshooter.domain.usecases

import com.netimur.buckshooter.data.model.Shell
import com.netimur.buckshooter.data.repository.ShellsRepository
import javax.inject.Inject

class LoadShellsUseCase @Inject constructor(
    private val shellsRepository: ShellsRepository
) {
    suspend operator fun invoke(shells: List<Shell>) {
        shellsRepository.addShells(shells = shells)
    }
}