package com.netimur.buckshooter.ui.gameprocess.components.counters

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun LiveShellsCount(modifier: Modifier = Modifier, LiveShellsCount: Int) {
    Text(
        modifier = modifier,
        text = "Live Shells: $LiveShellsCount"
    )
}