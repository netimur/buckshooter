package com.netimur.buckshooter.ui.gameprocess

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.netimur.buckshooter.ui.gameprocess.components.counters.BlankCartridgesCount
import com.netimur.buckshooter.ui.gameprocess.components.counters.CombatCartridgesCount
import com.netimur.buckshooter.ui.gameprocess.components.counters.TotalCartridgesCount
import com.netimur.buckshooter.ui.gameprocess.components.shootbuttons.ShootBlankButton
import com.netimur.buckshooter.ui.gameprocess.components.shootbuttons.ShootCombatButton
import com.netimur.buckshooter.ui.gameprocess.event.GameProcessEvent
import com.netimur.buckshooter.ui.gameprocess.event.ShootBlankEvent
import com.netimur.buckshooter.ui.gameprocess.event.ShootCombatEvent

@Composable
internal fun GameProcessScreen(viewModel: GameProcessViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    GameProcessScreenContent(uiState = uiState, handleEvent = viewModel::handleEvent)
}

@Composable
private fun GameProcessScreenContent(
    uiState: GameProcessUIState,
    handleEvent: (GameProcessEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                ShootBlankButton(
                    modifier = Modifier.fillMaxWidth(),
                    onShootBlank = { handleEvent(ShootBlankEvent) }
                )
                ShootCombatButton(
                    modifier = Modifier.fillMaxWidth(),
                    onShootCombat = { handleEvent(ShootCombatEvent) }
                )
            }
        }
    ) { scaffoldPaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = scaffoldPaddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TotalCartridgesCount(totalCount = uiState.cartridgesCount)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BlankCartridgesCount(blankCartridgesCount = uiState.blankCartridgesCount)
                CombatCartridgesCount(combatCartridgesCount = uiState.combatCartridgesCount)
            }
        }
    }
}

@Preview(locale = "kk")
@Preview(locale = "ru")
@Preview(locale = "en")
@PreviewLightDark
@Composable
private fun GameProcessScreenPreview() {
    GameProcessScreenContent(uiState = GameProcessUIState.empty) {}
}
