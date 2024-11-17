package com.netimur.buckshooter.ui.gameprocess.components.shootbuttons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.netimur.buckshooter.data.model.CartridgeType
import com.netimur.buckshooter.ui.utils.getSettingColor

@Composable
internal fun ShootButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    cartridgeType: CartridgeType
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = cartridgeType.getSettingColor()
        )
    ) {
        Text(
            text = "Shoot ${cartridgeType.name.lowercase()}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}