package com.netimur.buckshooter.ui.gamesetting.components.Shell

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
internal fun LiveShellController(
    modifier: Modifier = Modifier,
    LiveShellsCount: Int,
    onAddButtonClick: () -> Unit,
    onMinusButtonClick: () -> Unit,
    onSelectCountChips: (CountChipValue) -> Unit,
    onResetChipClick: () -> Unit,
    isCounterShaking: Boolean
) {
    Column(modifier = modifier) {
        Counter(count = LiveShellsCount, isShaking = isCounterShaking)
        ShellCard(
            shellType = ShellType.LIVE,
            onAddButtonClick = onAddButtonClick,
            onMinusButtonClick = onMinusButtonClick
        )
        CountChipsRow(
            lazyListState = rememberLazyListState(),
            chips = countChips,
            count = LiveShellsCount,
            onSelectChips = onSelectCountChips,
            onResetChipClick = onResetChipClick
        )
    }
}