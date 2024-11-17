package com.netimur.buckshooter.ui.gamesetting.components.countchips

private const val CHIPS_COUNT = 5

internal val countChips = List(CHIPS_COUNT) {
    CountChipValue(value = it + 1)
}