package com.netimur.buckshooter.ui.gamesetting.event

import com.netimur.buckshooter.ui.gamesetting.components.countchips.CountChipValue

internal data class SelectCombatCartridgeChipsEvent(val selectedChips: CountChipValue) :
    GameSettingEvent