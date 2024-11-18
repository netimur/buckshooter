package com.netimur.buckshooter.ui.gameprocess.event

import com.netimur.buckshooter.data.model.ShellType

internal data class SelectBurnerPhoneShellTypeEvent(val type: ShellType) : GameProcessEvent