package com.netimur.buckshooter.domain.usecases

import com.netimur.buckshooter.data.repository.ShellsRepository
import javax.inject.Inject

class ShootBlankShellUseCase @Inject constructor(
    private val shellsRepository: ShellsRepository
) {
    suspend operator fun invoke() {
        shellsRepository.removeBlankShell()
    }
}