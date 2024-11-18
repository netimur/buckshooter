package com.netimur.buckshooter.ui.gameprocess.components.shootbuttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.netimur.buckshooter.data.model.ShellType

@Composable
internal fun ShootLiveButton(modifier: Modifier = Modifier, onShootLive: () -> Unit) {
    ShootButton(
        modifier = modifier,
        onClick = onShootLive,
        shellType = ShellType.LIVE
    )
}