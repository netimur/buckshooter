package com.netimur.buckshooter.ui.gameprocess.event

import com.netimur.buckshooter.data.model.ShellType

internal data class UsePhoneEvent(
    val shellType: ShellType,
    val orderNumber: Int
) : GameProcessEvent