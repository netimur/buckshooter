package com.netimur.buckshooter.ui.gameprocess.components.alerts

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AreYouSureExitGameDialog(modifier: Modifier = Modifier, onDismiss: () -> Unit) {
    // TODO REFACTOR DESIGN
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Exit game?")
        },
        dismissButton = {
            Text(text = "cancel")
        },
        confirmButton = {
            Text(text = "exit")
        }
    )
}