package com.netimur.buckshooter.ui.gamesetting.components.countchips

import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun CountChip(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    value: Int,
    onClick: () -> Unit
) {
    InputChip(
        modifier = modifier,
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(text = value.toString())
        }
    )
}