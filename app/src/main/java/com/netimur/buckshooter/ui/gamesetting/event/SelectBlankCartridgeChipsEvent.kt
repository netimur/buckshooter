package com.netimur.buckshooter.ui.gamesetting.event

import com.netimur.buckshooter.ui.gamesetting.components.countchips.CountChipValue

internal data class SelectBlankCartridgeChipsEvent(val selectedChips: CountChipValue) :
    GameSettingEvent