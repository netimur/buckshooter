package com.netimur.buckshooter.ui.gameprocess.components.counters

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
internal fun TotalCartridgesCount(modifier: Modifier = Modifier, totalCount: Int) {
    Text(
        modifier = modifier,
        text = "Total cartridges: $totalCount"
    )
}