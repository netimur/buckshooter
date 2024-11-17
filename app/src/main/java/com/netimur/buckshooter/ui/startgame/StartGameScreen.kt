package com.netimur.buckshooter.ui.startgame

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark

@Composable
internal fun StartGameScreen(startGame: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = startGame
        ) {
            Text(text = "Start game")
        }
    }
}

@Preview(locale = "kk")
@Preview(locale = "ru")
@Preview(locale = "en")
@PreviewLightDark
@Composable
private fun StartGameScreenPreview() {
    StartGameScreen {  }
}