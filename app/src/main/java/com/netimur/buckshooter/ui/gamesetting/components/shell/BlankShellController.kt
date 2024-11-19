package com.netimur.buckshooter.ui.gamesetting.components.shell

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.netimur.buckshooter.data.model.ShellType
import com.netimur.buckshooter.ui.gamesetting.components.countchips.CountChipsRow
import com.netimur.buckshooter.ui.gamesetting.components.countchips.CountChipValue
import com.netimur.buckshooter.ui.gamesetting.components.countchips.countChips
import com.netimur.buckshooter.ui.gamesetting.components.counter.Counter

@Composable
internal fun BlankShellController(
    modifier: Modifier = Modifier,
    blankShellsCount: Int,
    onAddButtonClick: () -> Unit,
    onMinusButtonClick: () -> Unit,
    onSelectCountChips: (CountChipValue) -> Unit,
    onResetChipClick: () -> Unit,
    isCounterShaking: Boolean
) {
    Column(modifier = modifier) {
        Counter(count = blankShellsCount, isShaking = isCounterShaking)
        ShellCard(
            shellType = ShellType.BLANK,
            onAddButtonClick = onAddButtonClick,
            onMinusButtonClick = onMinusButtonClick
        )
        CountChipsRow(
            chips = countChips,
            count = blankShellsCount,
            onSelectChips = onSelectCountChips,
            lazyListState = rememberLazyListState(),
            onResetChipClick = onResetChipClick
        )
    }
}