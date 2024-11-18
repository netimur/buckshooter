package com.netimur.buckshooter.ui.gameprocess

import androidx.compose.runtime.Immutable

@Immutable
internal data class GameProcessUIState(
    val shellsCount: Int,
    val blankShellsCount: Int,
    val liveShellsCount: Int,
    val isInverterEnabled: Boolean,
    val burnerPhoneState: BurnerPhoneState
) {
    companion object {
        val empty = GameProcessUIState(
            shellsCount = 0,
            blankShellsCount = 0,
            liveShellsCount = 0,
            isInverterEnabled = false,
            burnerPhoneState = BurnerPhoneState(
                isExpanded = false,
                selectedShellType = null,
                selectedOrderNumber = null
            )
        )
    }
}