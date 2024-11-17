package com.netimur.buckshooter.ui.gameprocess.components.counters

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun CombatCartridgesCount(modifier: Modifier = Modifier, combatCartridgesCount: Int) {
    Text(
        modifier = modifier,
        text = "Combat cartridges: $combatCartridgesCount"
    )
}