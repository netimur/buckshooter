package com.netimur.buckshooter.ui.gamesetting.components.shell

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.netimur.buckshooter.data.model.ShellType
import com.netimur.buckshooter.ui.utils.getSettingColor

@Composable
internal fun MinusButton(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = "-",
        style = MaterialTheme.typography.displaySmall,
        color = MaterialTheme.colorScheme.tertiary
    )
}