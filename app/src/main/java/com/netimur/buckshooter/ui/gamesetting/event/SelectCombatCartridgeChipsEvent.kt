package com.netimur.buckshooter.ui.gamesetting.event

import com.netimur.buckshooter.ui.gamesetting.components.countchips.CountChipValue

internal data class SelectLiveShellChipsEvent(val selectedChips: CountChipValue) :
    GameSettingEvent