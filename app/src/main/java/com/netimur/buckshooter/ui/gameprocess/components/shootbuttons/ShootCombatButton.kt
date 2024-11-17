package com.netimur.buckshooter.ui.gameprocess.components.shootbuttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.netimur.buckshooter.data.model.CartridgeType

@Composable
internal fun ShootCombatButton(modifier: Modifier = Modifier, onShootCombat: () -> Unit) {
    ShootButton(
        modifier = modifier,
        onClick = onShootCombat,
        cartridgeType = CartridgeType.COMBAT
    )
}