package com.netimur.buckshooter.domain.usecases

import com.netimur.buckshooter.data.model.Cartridge
import com.netimur.buckshooter.data.repository.CartridgesRepository
import javax.inject.Inject

class LoadCartridgesUseCase @Inject constructor(
    private val cartridgesRepository: CartridgesRepository
) {
    suspend operator fun invoke(cartridges: List<Cartridge>) {
        cartridgesRepository.addCartridges(cartridges = cartridges)
    }
}