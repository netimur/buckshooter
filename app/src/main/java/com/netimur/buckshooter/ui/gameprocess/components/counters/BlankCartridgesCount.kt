package com.netimur.buckshooter.ui.gameprocess.components.counters

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun BlankCartridgesCount(modifier: Modifier = Modifier, blankCartridgesCount: Int) {
    Text(
        modifier = modifier,
        text = "Blank cartridges: $blankCartridgesCount"
    )
}