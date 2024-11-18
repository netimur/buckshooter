package com.netimur.buckshooter.ui.gameprocess.components.phone

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.netimur.buckshooter.data.model.ShellType
import com.netimur.buckshooter.ui.gamesetting.components.countchips.CountChipValue
import com.netimur.buckshooter.ui.gamesetting.components.countchips.CountChipsRow
import com.netimur.buckshooter.ui.gamesetting.components.countchips.countChips
import com.netimur.buckshooter.ui.utils.getSettingColor

@Composable
internal fun UsePhoneButton(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    onCloseButtonClick: () -> Unit,
    onPhoneButtonClick: () -> Unit,
    chips: List<CountChipValue>,
    selectedOrderNumber: Int?,
    selectedShellType: ShellType?,
    onSelectOrderNumber: (CountChipValue) -> Unit,
    onSelectShellType: (ShellType) -> Unit,
    onResetChipsClick: () -> Unit
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val disableColor = MaterialTheme.colorScheme.tertiary

    val buttonColor by remember(isExpanded) {
        mutableStateOf(
            if (isExpanded) {
                disableColor
            } else {
                primaryColor
            }
        )
    }

    val animatedButtonColor by animateColorAsState(
        targetValue = buttonColor,
        label = "Animated button color"
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val expandedButtonCornerPercentage = 100F
        val collapsedButtonCornerPercentage = 50F

        val cornerPercentage by remember(isExpanded) {
            mutableFloatStateOf(
                if (isExpanded) {
                    expandedButtonCornerPercentage
                } else {
                    collapsedButtonCornerPercentage
                }
            )
        }

        val animatedCornerSize by animateFloatAsState(
            targetValue = cornerPercentage,
            label = "animated corner size"
        )

        Box(
            modifier = Modifier
                .clickable(onClick = onPhoneButtonClick)
                .background(
                    color = animatedButtonColor,
                    shape = RoundedCornerShape(size = animatedCornerSize)
                )
                .clip(
                    shape = RoundedCornerShape(size = animatedCornerSize)
                ),
            contentAlignment = Alignment.Center,
        ) {
            AnimatedContent(
                targetState = isExpanded,
                label = "Main button animated content"
            ) { isExpanded ->
                if (isExpanded) {
                    IconButton(onClick = onCloseButtonClick) {
                        Icon(imageVector = Icons.Outlined.Close, contentDescription = null)
                    }
                } else {
                    IconButton(onClick = onPhoneButtonClick) {
                        Icon(imageVector = Icons.Outlined.Phone, contentDescription = null)
                    }
                }

            }
        }

        AnimatedVisibility(visible = isExpanded) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
                ) {
                    ShellTypeInputChip(
                        shellType = ShellType.LIVE,
                        isSelected = selectedShellType == ShellType.LIVE,
                        onClick = {
                            onSelectShellType(ShellType.LIVE)
                        }
                    )
                    ShellTypeInputChip(
                        shellType = ShellType.BLANK,
                        isSelected = selectedShellType == ShellType.BLANK,
                        onClick = {
                            onSelectShellType(ShellType.BLANK)
                        }
                    )
                }
                CountChipsRow(
                    lazyListState = rememberLazyListState(),
                    chips = chips,
                    onResetChipClick = onResetChipsClick,
                    count = selectedOrderNumber ?: Int.MIN_VALUE,
                    onSelectChips = onSelectOrderNumber
                )
            }

        }
    }
}

@Composable
private fun ShellTypeInputChip(
    modifier: Modifier = Modifier,
    shellType: ShellType,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    InputChip(
        modifier = modifier,
        colors = InputChipDefaults.inputChipColors().copy(
            containerColor = shellType.getSettingColor()
        ),
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(
                text = shellType.name.lowercase(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = if (isSelected) {
                        MaterialTheme.colorScheme.onSurface
                    } else {
                        MaterialTheme.colorScheme.onSecondary
                    }
                )
            )
        }
    )
}

@Preview(locale = "kk")
@Preview(locale = "ru")
@Preview(locale = "en")
@PreviewLightDark
@Composable
private fun UsePhoneButtonPreview() {
    var isExpanded by remember {
        mutableStateOf(true)
    }
    UsePhoneButton(
        isExpanded = isExpanded,
        onPhoneButtonClick = {
            isExpanded = true
        },
        selectedOrderNumber = null,
        selectedShellType = ShellType.BLANK,
        onSelectShellType = {},
        onSelectOrderNumber = {},
        onCloseButtonClick = {
            isExpanded = false
        },
        chips = countChips,
        onResetChipsClick = {}
    )
}