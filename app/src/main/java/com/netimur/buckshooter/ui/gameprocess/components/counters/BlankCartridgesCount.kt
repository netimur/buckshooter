package com.netimur.buckshooter.ui.gameprocess.components.counters

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun BlankShellsCount(modifier: Modifier = Modifier, blankShellsCount: Int) {
    Text(
        modifier = modifier,
        text = "Blank Shells: $blankShellsCount"
    )
}