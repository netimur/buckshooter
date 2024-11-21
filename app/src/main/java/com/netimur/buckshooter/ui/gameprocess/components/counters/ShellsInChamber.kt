package com.netimur.buckshooter.ui.gameprocess.components.counters

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun ShellsInChamber(modifier: Modifier = Modifier, shellsInChamberCount: Int) {
    Text(
        modifier = modifier,
        // TODO REFACTOR use stringResource with arguments, style names of shell types
        text = "$shellsInChamberCount shells in chamber",
        style = MaterialTheme.typography.titleMedium
    )
}