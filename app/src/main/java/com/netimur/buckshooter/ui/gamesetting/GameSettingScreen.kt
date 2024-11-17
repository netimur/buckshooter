package com.netimur.buckshooter.ui.gamesetting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.netimur.buckshooter.ui.gamesetting.components.cartridge.BlankCartridgeController
import com.netimur.buckshooter.ui.gamesetting.components.cartridge.CombatCartridgeController
import com.netimur.buckshooter.ui.gamesetting.event.AddBlankCartridgeEvent
import com.netimur.buckshooter.ui.gamesetting.event.AddCombatCartridgeEvent
import com.netimur.buckshooter.ui.gamesetting.event.GameSettingEvent
import com.netimur.buckshooter.ui.gamesetting.event.MinusBlankCartridgeEvent
import com.netimur.buckshooter.ui.gamesetting.event.MinusCombatCartridgeEvent
import com.netimur.buckshooter.ui.gamesetting.event.ResetBlankCartridgesCountEvent
import com.netimur.buckshooter.ui.gamesetting.event.ResetCombatCartridgesCountEvent
import com.netimur.buckshooter.ui.gamesetting.event.SelectBlankCartridgeChipsEvent
import com.netimur.buckshooter.ui.gamesetting.event.SelectCombatCartridgeChipsEvent

@Composable
internal fun GameSettingScreen(startGame: () -> Unit, viewModel: GameSettingViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    GameSettingScreenContent(
        uiState = uiState,
        onApplySettingButtonClick = startGame,
        handleEvent = viewModel::handleEvent
    )
}

@Composable
private fun GameSettingScreenContent(
    uiState: GameSettingUIState,
    onApplySettingButtonClick: () -> Unit,
    handleEvent: (event: GameSettingEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        bottomBar = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column {
                        BlankCartridgeController(
                            modifier = Modifier.fillMaxWidth(),
                            blankCartridgesCount = uiState.blankCount,
                            onAddButtonClick = {
                                handleEvent(AddBlankCartridgeEvent)
                            },
                            onMinusButtonClick = {
                                handleEvent(MinusBlankCartridgeEvent)
                            },
                            onSelectCountChips = { selectedChips ->
                                handleEvent(SelectBlankCartridgeChipsEvent(selectedChips = selectedChips))
                            },
                            onResetChipClick = {
                                handleEvent(ResetBlankCartridgesCountEvent)
                            },
                            isCounterShaking = uiState.isBlankCounterShaking
                        )
                        CombatCartridgeController(
                            modifier = Modifier.fillMaxWidth(),
                            combatCartridgesCount = uiState.combatCount,
                            onAddButtonClick = {
                                handleEvent(AddCombatCartridgeEvent)
                            },
                            onMinusButtonClick = {
                                handleEvent(MinusCombatCartridgeEvent)
                            },
                            onSelectCountChips = { selectedChips ->
                                handleEvent(SelectCombatCartridgeChipsEvent(selectedChips))
                            },
                            onResetChipClick = {
                                handleEvent(ResetCombatCartridgesCountEvent)
                            },
                            isCounterShaking = uiState.isCombatCounterShaking
                        )
                    }
                }
                ApplyGameSettingButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    onClick = onApplySettingButtonClick
                )
            }

        }
    ) { scaffoldPaddingValues ->
        scaffoldPaddingValues
    }
}

@Composable
private fun ApplyGameSettingButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(text = "Apply setting")
    }
}

@Preview(locale = "kk")
@Preview(locale = "ru")
@Preview(locale = "en")
@PreviewLightDark
@Composable
private fun GameSettingScreenPreview() {
    GameSettingScreenContent(
        uiState = GameSettingUIState(0, 0, false, false),
        onApplySettingButtonClick = {},
        handleEvent = {}
    )
}