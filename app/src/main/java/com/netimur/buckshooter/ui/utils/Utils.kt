package com.netimur.buckshooter.ui.utils

import androidx.compose.ui.graphics.Color
import com.netimur.buckshooter.data.model.CartridgeType

internal fun CartridgeType.getSettingColor(): Color {
    return when (this) {
        CartridgeType.BLANK -> Color(0xFF1770B8)
        CartridgeType.COMBAT -> Color(0xFFB81717)
    }
}