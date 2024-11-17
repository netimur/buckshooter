package com.netimur.buckshooter.domain.usecases

import com.netimur.buckshooter.data.model.Cartridge
import com.netimur.buckshooter.data.model.CartridgeType
import com.netimur.buckshooter.data.repository.CartridgesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObserveCombatCartridgesUseCase @Inject constructor(
    private val cartridgesRepository: CartridgesRepository
) {
    operator fun invoke(): Flow<List<Cartridge>> {
        return cartridgesRepository.observeCartridges().map { cartridges ->
            cartridges.filter { cartridge ->
                cartridge.cartridgeType == CartridgeType.COMBAT
            }
        }
    }
}