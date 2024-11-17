package com.netimur.buckshooter.domain.usecases

import com.netimur.buckshooter.data.repository.CartridgesRepository
import javax.inject.Inject

class ShootCombatCartridgeUseCase @Inject constructor(
    private val cartridgesRepository: CartridgesRepository
) {
    suspend operator fun invoke() {
        cartridgesRepository.removeCombatCartridge()
    }
}