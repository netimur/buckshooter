package com.netimur.buckshooter.ui.gameprocess.components.shootbuttons

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.netimur.buckshooter.data.model.ShellType

@Composable
internal fun ShootButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    shellType: ShellType
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = "Shoot ${shellType.name.lowercase()}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}