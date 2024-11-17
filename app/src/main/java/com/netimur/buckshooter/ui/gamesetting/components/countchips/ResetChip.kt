package com.netimur.buckshooter.ui.gamesetting.components.countchips

import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun ResetChip(modifier: Modifier = Modifier, onClick: () -> Unit) {
    AssistChip(
        modifier = modifier,
        onClick = onClick,
        label = {
            Text(text = "Reset")
        }
    )
}