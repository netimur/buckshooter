package com.netimur.buckshooter.ui.gamesetting.components.cartridge

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.netimur.buckshooter.data.model.CartridgeType
import com.netimur.buckshooter.ui.gamesetting.components.countchips.CountChipsRow
import com.netimur.buckshooter.ui.gamesetting.components.countchips.CountChipValue
import com.netimur.buckshooter.ui.gamesetting.components.countchips.countChips
import com.netimur.buckshooter.ui.gamesetting.components.counter.Counter

@Composable
internal fun BlankCartridgeController(
    modifier: Modifier = Modifier,
    blankCartridgesCount: Int,
    onAddButtonClick: () -> Unit,
    onMinusButtonClick: () -> Unit,
    onSelectCountChips: (CountChipValue) -> Unit,
    onResetChipClick: () -> Unit,
    isCounterShaking: Boolean
) {
    Column(modifier = modifier) {
        Counter(count = blankCartridgesCount, isShaking = isCounterShaking)
        CartridgeCard(
            cartridgeType = CartridgeType.BLANK,
            onAddButtonClick = onAddButtonClick,
            onMinusButtonClick = onMinusButtonClick
        )
        CountChipsRow(
            chips = countChips,
            count = blankCartridgesCount,
            onSelectChips = onSelectCountChips,
            lazyListState = rememberLazyListState(),
            onResetChipClick = onResetChipClick
        )
    }
}