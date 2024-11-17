package com.netimur.buckshooter.ui.gamesetting

import androidx.compose.runtime.Immutable

@Immutable
data class GameSettingUIState(
    val blankCount: Int,
    val combatCount: Int,
    val isBlankCounterShaking: Boolean,
    val isCombatCounterShaking: Boolean,
) {
    companion object {
        val empty = GameSettingUIState(
            blankCount = 0,
            combatCount = 0,
            isCombatCounterShaking = false,
            isBlankCounterShaking = false
        )
    }
}