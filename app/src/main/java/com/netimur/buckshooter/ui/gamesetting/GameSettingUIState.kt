package com.netimur.buckshooter.ui.gamesetting

import androidx.compose.runtime.Immutable

@Immutable
data class GameSettingUIState(
    val blankCount: Int,
    val LiveCount: Int,
    val isBlankCounterShaking: Boolean,
    val isLiveCounterShaking: Boolean,
) {
    companion object {
        val empty = GameSettingUIState(
            blankCount = 0,
            LiveCount = 0,
            isLiveCounterShaking = false,
            isBlankCounterShaking = false
        )
    }
}