package com.netimur.buckshooter.ui.gameprocess

import com.netimur.buckshooter.data.model.ShellType

internal data class BurnerPhoneState(
    val isExpanded: Boolean,
    val selectedShellType: ShellType?,
    val selectedOrderNumber: Int?
)