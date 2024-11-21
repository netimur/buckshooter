package com.netimur.buckshooter.ui.gameprocess.components.bottomsheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.netimur.buckshooter.core.designsystem.grid.Spaces
import com.netimur.buckshooter.data.model.ShellType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BurnerPhoneBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onApplyButtonClick: () -> Unit,
    sheetState: SheetState,
    onSelectBlankShell: () -> Unit,
    onSelectLiveShell: () -> Unit,
    selectedShellType: ShellType?
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        content = {
            Scaffold(
                modifier = Modifier.fillMaxWidth(),
                topBar = {
                    UseBurnerPhoneHeader()
                },
                content = { scaffoldPaddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(paddingValues = scaffoldPaddingValues)
                    ) {
                        OrdinalNumberTitle(modifier = Modifier.padding(start = Spaces.m))
                        ShellTypeTitle(modifier = Modifier.padding(start = Spaces.m))
                        Row {
                            LiveShellChip(
                                isSelected = selectedShellType == ShellType.LIVE,
                                onClick = onSelectLiveShell
                            )
                            BlankShellChip(
                                isSelected = selectedShellType == ShellType.BLANK,
                                onClick = onSelectBlankShell
                            )
                        }
                    }
                },
                bottomBar = {
                    ApplyButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Spaces.m),
                        onClick = onApplyButtonClick
                    )
                }
            )
        }
    )
}

@Composable
private fun OrdinalNumberTitle(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = "Ordinal number",
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
private fun ShellTypeTitle(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = "Shell type",
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
private fun LiveShellChip(modifier: Modifier = Modifier, isSelected: Boolean, onClick: () -> Unit) {
    InputChip(
        modifier = modifier,
        selected = isSelected,
        label = {
            "Live shell"
        },
        onClick = onClick
    )
}

@Composable
private fun BlankShellChip(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    InputChip(
        modifier = modifier,
        selected = isSelected,
        label = {
            "Blank shell"
        },
        onClick = onClick
    )
}

@Composable
private fun UseBurnerPhoneHeader(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = "Use burner phone",
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
private fun ApplyButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(text = "Apply")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@PreviewLightDark
@Composable
private fun Preview() {

    BurnerPhoneBottomSheet(
        onDismissRequest = {},
        sheetState = rememberModalBottomSheetState(),
        onApplyButtonClick = {},
        onSelectLiveShell = {},
        onSelectBlankShell = {},
        selectedShellType = null
    )
}