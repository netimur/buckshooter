package com.netimur.buckshooter.ui.gameprocess.components.shootbuttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.netimur.buckshooter.data.model.ShellType

@Composable
internal fun ShootBlankButton(modifier: Modifier = Modifier, onShootBlank: () -> Unit) {
    ShootButton(
        modifier = modifier,
        onClick = onShootBlank,
        shellType = ShellType.BLANK
    )
}