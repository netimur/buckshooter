package com.netimur.buckshooter.ui.gameprocess

import androidx.compose.runtime.Immutable

@Immutable
internal data class GameProcessUIState(
    val cartridgesCount: Int,
    val blankCartridgesCount: Int,
    val combatCartridgesCount: Int,
    val isCartridgeSwapUsed: Boolean
) {
    companion object {
        val empty = GameProcessUIState(
            cartridgesCount = 10,
            blankCartridgesCount = 3,
            combatCartridgesCount = 7,
            isCartridgeSwapUsed = false
        )
    }
}