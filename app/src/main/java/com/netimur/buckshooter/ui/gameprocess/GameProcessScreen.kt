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
import com.netimur.buckshooter.ui.gameprocess.components.counters.BlankShellsCount
import com.netimur.buckshooter.ui.gameprocess.components.counters.LiveShellsCount
import com.netimur.buckshooter.ui.gameprocess.components.counters.TotalShellsCount
import com.netimur.buckshooter.ui.gameprocess.components.phone.UsePhoneButton
import com.netimur.buckshooter.ui.gameprocess.components.shootbuttons.ShootBlankButton
import com.netimur.buckshooter.ui.gameprocess.components.shootbuttons.ShootLiveButton
import com.netimur.buckshooter.ui.gameprocess.event.BurnerPhoneClickEvent
import com.netimur.buckshooter.ui.gameprocess.event.CloseBurnerPhoneEvent
import com.netimur.buckshooter.ui.gameprocess.event.GameProcessEvent
import com.netimur.buckshooter.ui.gameprocess.event.ResetBurnerPhoneOrderNumberEvent
import com.netimur.buckshooter.ui.gameprocess.event.SelectBurnerPhoneShellOrderEvent
import com.netimur.buckshooter.ui.gameprocess.event.SelectBurnerPhoneShellTypeEvent
import com.netimur.buckshooter.ui.gameprocess.event.ShootBlankEvent
import com.netimur.buckshooter.ui.gameprocess.event.ShootLiveEvent
import com.netimur.buckshooter.ui.gamesetting.components.countchips.countChips

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
                ShootLiveButton(
                    modifier = Modifier.fillMaxWidth(),
                    onShootLive = { handleEvent(ShootLiveEvent) }
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
            TotalShellsCount(totalCount = uiState.shellsCount)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BlankShellsCount(blankShellsCount = uiState.blankShellsCount)
                LiveShellsCount(LiveShellsCount = uiState.liveShellsCount)
            }
            UsePhoneButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                isExpanded = uiState.burnerPhoneState.isExpanded,
                onCloseButtonClick = {
                    handleEvent(CloseBurnerPhoneEvent)
                },
                onPhoneButtonClick = {
                    handleEvent(BurnerPhoneClickEvent)
                },
                // TODO REPLACE WITH value from state holder
                chips = countChips,
                selectedOrderNumber = uiState.burnerPhoneState.selectedOrderNumber,
                selectedShellType = uiState.burnerPhoneState.selectedShellType,
                onSelectShellType = { shellType ->
                    handleEvent(SelectBurnerPhoneShellTypeEvent(type = shellType))
                },
                onResetChipsClick = {
                    handleEvent(ResetBurnerPhoneOrderNumberEvent)
                },
                onSelectOrderNumber = { chipValue ->
                    handleEvent(SelectBurnerPhoneShellOrderEvent(orderNumber = chipValue.value))
                }
            )
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
