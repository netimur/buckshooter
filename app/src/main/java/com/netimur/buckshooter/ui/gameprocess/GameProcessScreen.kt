package com.netimur.buckshooter.ui.gameprocess

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.netimur.buckshooter.data.model.ShellType
import com.netimur.buckshooter.ui.gameprocess.alert.AreYouSureExitGameAlert
import com.netimur.buckshooter.ui.gameprocess.alert.GameProcessAlert
import com.netimur.buckshooter.ui.gameprocess.bottomsheet.BurnerPhoneBottomSheet
import com.netimur.buckshooter.ui.gameprocess.bottomsheet.NoneBottomSheet
import com.netimur.buckshooter.ui.gameprocess.components.alerts.AreYouSureExitGameDialog
import com.netimur.buckshooter.ui.gameprocess.components.bottomsheets.BurnerPhoneBottomSheet
import com.netimur.buckshooter.ui.gameprocess.components.counters.BlankShellsCount
import com.netimur.buckshooter.ui.gameprocess.components.counters.LiveShellsCount
import com.netimur.buckshooter.ui.gameprocess.components.counters.ShellsInChamber
import com.netimur.buckshooter.ui.gameprocess.components.shootbuttons.ShootBlankButton
import com.netimur.buckshooter.ui.gameprocess.components.shootbuttons.ShootLiveButton
import com.netimur.buckshooter.ui.gameprocess.event.BurnerPhoneClickEvent
import com.netimur.buckshooter.ui.gameprocess.event.DismissDialogEvent
import com.netimur.buckshooter.ui.gameprocess.event.EndGameButtonClickEvent
import com.netimur.buckshooter.ui.gameprocess.event.GameProcessEvent
import com.netimur.buckshooter.ui.gameprocess.event.ShootBlankEvent
import com.netimur.buckshooter.ui.gameprocess.event.ShootLiveEvent
import kotlinx.coroutines.launch

@Composable
internal fun GameProcessScreen(viewModel: GameProcessViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    GameProcessScreenContent(uiState = uiState, handleEvent = viewModel::handleEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
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
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ShootBlankButton(
                    modifier = Modifier.fillMaxWidth(),
                    onShootBlank = { handleEvent(ShootBlankEvent) }
                )
                ShootLiveButton(
                    modifier = Modifier.fillMaxWidth(),
                    onShootLive = { handleEvent(ShootLiveEvent) }
                )
                EndGameButton(onClick = { handleEvent(EndGameButtonClickEvent) })
            }
        },
        floatingActionButton = {
            ExtraItemsFloatingActionButton { handleEvent(BurnerPhoneClickEvent) }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { scaffoldPaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = scaffoldPaddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CurrentShellTitle(currentShellType = uiState.currentShellType)
            ShellsInChamber(shellsInChamberCount = uiState.shellsCount)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                BlankShellsCount(blankShellsCount = uiState.blankShellsCount)
                LiveShellsCount(liveShellsCount = uiState.liveShellsCount)
            }
        }
        val sheetState = rememberModalBottomSheetState()
        val coroutineScope = rememberCoroutineScope()
        when (uiState.bottomSheet) {
            BurnerPhoneBottomSheet -> {
                BurnerPhoneBottomSheet(
                    onDismissRequest = {},
                    onSelectLiveShell = {},
                    onSelectBlankShell = {},
                    onApplyButtonClick = {},
                    sheetState = sheetState,
                    modifier = Modifier,
                    selectedShellType = uiState.currentShellType,
                )
                coroutineScope.launch {
                    sheetState.show()
                }
            }

            NoneBottomSheet -> {}
        }

        when (uiState.alert) {
            AreYouSureExitGameAlert -> {
                AreYouSureExitGameDialog(
                    onDismiss = {
                        handleEvent(DismissDialogEvent)
                    }
                )
            }

            GameProcessAlert.NoneAlert -> {}
        }
    }
}

@Composable
private fun EndGameButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = "End game",
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
private fun ExtraItemsFloatingActionButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        content = {
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = null
            )
        }
    )
}

@Composable
private fun CurrentShellTitle(modifier: Modifier = Modifier, currentShellType: ShellType) {
    Text(
        modifier = modifier,
        // TODO REFACTOR use stringResource with arguments, style names of shell types
        text = "Current shell is: ${currentShellType.name}",
        style = MaterialTheme.typography.titleMedium
    )
}

@Preview(locale = "kk")
@Preview(locale = "ru")
@Preview(locale = "en")
@PreviewLightDark
@Composable
private fun GameProcessScreenPreview() {
    GameProcessScreenContent(uiState = GameProcessUIState.empty) {}
}


/*UsePhoneButton(
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
            )*/