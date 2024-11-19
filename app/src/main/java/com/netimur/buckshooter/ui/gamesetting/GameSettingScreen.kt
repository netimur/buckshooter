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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.netimur.buckshooter.ui.gamesetting.components.shell.BlankShellController
import com.netimur.buckshooter.ui.gamesetting.components.shell.LiveShellController
import com.netimur.buckshooter.ui.gamesetting.event.AddBlankShellEvent
import com.netimur.buckshooter.ui.gamesetting.event.AddLiveShellEvent
import com.netimur.buckshooter.ui.gamesetting.event.ApplySettingEvent
import com.netimur.buckshooter.ui.gamesetting.event.GameSettingEvent
import com.netimur.buckshooter.ui.gamesetting.event.MinusBlankShellEvent
import com.netimur.buckshooter.ui.gamesetting.event.MinusLiveShellEvent
import com.netimur.buckshooter.ui.gamesetting.event.ResetBlankShellsCountEvent
import com.netimur.buckshooter.ui.gamesetting.event.ResetLiveShellsCountEvent
import com.netimur.buckshooter.ui.gamesetting.event.SelectBlankShellChipsEvent
import com.netimur.buckshooter.ui.gamesetting.event.SelectLiveShellChipsEvent

@Composable
internal fun GameSettingScreen(startGame: () -> Unit, viewModel: GameSettingViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect {
            startGame()
        }
    }
    GameSettingScreenContent(
        uiState = uiState,
        handleEvent = viewModel::handleEvent
    )
}

@Composable
private fun GameSettingScreenContent(
    uiState: GameSettingUIState,
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
                        BlankShellController(
                            modifier = Modifier.fillMaxWidth(),
                            blankShellsCount = uiState.blankCount,
                            onAddButtonClick = {
                                handleEvent(AddBlankShellEvent)
                            },
                            onMinusButtonClick = {
                                handleEvent(MinusBlankShellEvent)
                            },
                            onSelectCountChips = { selectedChips ->
                                handleEvent(SelectBlankShellChipsEvent(selectedChips = selectedChips))
                            },
                            onResetChipClick = {
                                handleEvent(ResetBlankShellsCountEvent)
                            },
                            isCounterShaking = uiState.isBlankCounterShaking
                        )
                        LiveShellController(
                            modifier = Modifier.fillMaxWidth(),
                            liveShellsCount = uiState.LiveCount,
                            onAddButtonClick = {
                                handleEvent(AddLiveShellEvent)
                            },
                            onMinusButtonClick = {
                                handleEvent(MinusLiveShellEvent)
                            },
                            onSelectCountChips = { selectedChips ->
                                handleEvent(SelectLiveShellChipsEvent(selectedChips))
                            },
                            onResetChipClick = {
                                handleEvent(ResetLiveShellsCountEvent)
                            },
                            isCounterShaking = uiState.isLiveCounterShaking
                        )
                    }
                }
                ApplyGameSettingButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    onClick = { handleEvent(ApplySettingEvent) }
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
        handleEvent = {}
    )
}