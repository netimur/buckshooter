package com.netimur.buckshooter.domain.usecases

import com.netimur.buckshooter.data.repository.CartridgesRepository
import javax.inject.Inject

class ShootBlankCartridgeUseCase @Inject constructor(
    private val cartridgesRepository: CartridgesRepository
) {
    suspend operator fun invoke() {
        cartridgesRepository.removeBlankCartridge()
    }
}