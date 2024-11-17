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
internal fun CombatCartridgeController(
    modifier: Modifier = Modifier,
    combatCartridgesCount: Int,
    onAddButtonClick: () -> Unit,
    onMinusButtonClick: () -> Unit,
    onSelectCountChips: (CountChipValue) -> Unit,
    onResetChipClick: () -> Unit,
    isCounterShaking: Boolean
) {
    Column(modifier = modifier) {
        Counter(count = combatCartridgesCount, isShaking = isCounterShaking)
        CartridgeCard(
            cartridgeType = CartridgeType.COMBAT,
            onAddButtonClick = onAddButtonClick,
            onMinusButtonClick = onMinusButtonClick
        )
        CountChipsRow(
            lazyListState = rememberLazyListState(),
            chips = countChips,
            count = combatCartridgesCount,
            onSelectChips = onSelectCountChips,
            onResetChipClick = onResetChipClick
        )
    }
}