package com.netimur.buckshooter.ui.utils

import androidx.compose.ui.graphics.Color
import com.netimur.buckshooter.data.model.ShellType

internal fun ShellType.getSettingColor(): Color {
    return when (this) {
        ShellType.BLANK -> Color(0xFF1770B8)
        ShellType.LIVE -> Color(0xFFB81717)
        ShellType.UNKNOWN -> Color.White
    }
}