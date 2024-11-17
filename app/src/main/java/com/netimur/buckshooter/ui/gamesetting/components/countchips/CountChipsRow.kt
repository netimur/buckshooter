package com.netimur.buckshooter.ui.gamesetting.components.countchips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark

@Composable
internal fun CountChipsRow(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
    chips: List<CountChipValue>,
    count: Int,
    onSelectChips: (selectedChips: CountChipValue) -> Unit,
    onResetChipClick: () -> Unit
) {
    LazyRow(
        modifier = modifier,
        state = lazyListState,
        horizontalArrangement = Arrangement.Start
    ) {
        items(
            items = chips,
            key = { it.value }
        ) { chips ->
            CountChip(
                isSelected = chips.value == count,
                onClick = {
                    onSelectChips(chips)
                },
                value = chips.value
            )
        }
        item {
            ResetChip(onClick = onResetChipClick)
        }
    }
}

@Preview(locale = "kk")
@Preview(locale = "ru")
@Preview(locale = "en")
@PreviewLightDark
@Composable
private fun CountChipsRowPreview() {
    val chips = List(5) {
        CountChipValue(it)
    }
    CountChipsRow(
        chips = chips,
        count = 2,
        lazyListState = rememberLazyListState(),
        onResetChipClick = {},
        onSelectChips = {}
    )
}