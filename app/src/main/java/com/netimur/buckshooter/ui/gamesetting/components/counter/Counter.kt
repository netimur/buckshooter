package com.netimur.buckshooter.ui.gamesetting.components.counter

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import kotlinx.coroutines.launch

@Composable
internal fun Counter(modifier: Modifier = Modifier, count: Int, isShaking: Boolean) {
    // TODO USE ANOTHER ANIMATION API WITH ONE-SHOT EVENTS
    var translation by remember {
        mutableFloatStateOf(0.5F)
    }

    var rotation by remember {
        mutableFloatStateOf(0F)
    }

    suspend fun animateRotation() {
        animate(
            initialValue = -10F,
            targetValue = 10F,
            animationSpec = infiniteRepeatable(
                tween(50),
                RepeatMode.Reverse
            )
        ) { value, _ ->
            rotation = value
        }
    }

    suspend fun animateTranslation() {
        animate(
            initialValue = 0.1F,
            targetValue = 20F,
            animationSpec = infiniteRepeatable(
                tween(50),
                RepeatMode.Reverse
            )
        ) { value, _ ->
            translation = value
        }
    }

    LaunchedEffect(key1 = isShaking) {
        if (isShaking) {
            launch { animateRotation() }
            launch { animateTranslation() }
        }
    }

    Text(
        modifier = modifier.graphicsLayer {
            translationX = translation
            rotationZ = rotation
        },
        text = count.toString(),
        style = MaterialTheme.typography.displaySmall.copy(textMotion = TextMotion.Animated)
    )
}

@Preview(locale = "kk")
@Preview(locale = "ru")
@Preview(locale = "en")
@PreviewLightDark
@Composable
private fun CounterPreview() {
    Counter(count = 3, isShaking = false)
}