package com.netimur.buckshooter.ui.gamesetting.components.cartridge

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.netimur.buckshooter.ui.gamesetting.CartridgeType
import com.netimur.buckshooter.ui.utils.getSettingColor

@Composable
internal fun CartridgeCard(
    modifier: Modifier = Modifier, cartridgeType: CartridgeType,
    onAddButtonClick: () -> Unit,
    onMinusButtonClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(corner = CornerSize(size = 16.dp))
            )
            .border(
                width = 2.dp,
                color = cartridgeType.getSettingColor(),
                shape = RoundedCornerShape(corner = CornerSize(size = 16.dp))
            )
            .clip(
                shape = RoundedCornerShape(corner = CornerSize(size = 16.dp))
            )
            .clickable(onClick = onMinusButtonClick),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = cartridgeType.getSettingColor(),
                        shape = RoundedCornerShape(corner = CornerSize(size = 16.dp))
                    )
                    .padding(end = 16.dp)
                    .clip(
                        shape = RoundedCornerShape(corner = CornerSize(size = 16.dp))
                    )
                    .clickable(onClick = onAddButtonClick)
                    .fillMaxWidth(0.8F),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(all = 16.dp),
                    text = "Add ${cartridgeType.name.lowercase()}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            MinusButton(cartridgeType = cartridgeType)
        }
    }
}