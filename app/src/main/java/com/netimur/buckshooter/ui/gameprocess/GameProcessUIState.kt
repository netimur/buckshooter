package com.netimur.buckshooter.ui.gameprocess

import androidx.compose.runtime.Immutable
import com.netimur.buckshooter.data.model.ShellType
import com.netimur.buckshooter.ui.gameprocess.alert.GameProcessAlert
import com.netimur.buckshooter.ui.gameprocess.bottomsheet.GameProcessBottomSheet
import com.netimur.buckshooter.ui.gameprocess.bottomsheet.NoneBottomSheet

@Immutable
internal data class GameProcessUIState(
    val shellsCount: Int,
    val blankShellsCount: Int,
    val liveShellsCount: Int,
    val isInverterEnabled: Boolean,
    val burnerPhoneState: BurnerPhoneState,
    val currentShellType: ShellType,
    val bottomSheet: GameProcessBottomSheet,
    val alert: GameProcessAlert
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
            ),
            currentShellType = ShellType.UNKNOWN,
            bottomSheet = NoneBottomSheet,
            alert = GameProcessAlert.NoneAlert
        )
    }
}