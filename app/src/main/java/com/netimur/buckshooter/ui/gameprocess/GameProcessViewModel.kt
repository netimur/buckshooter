package com.netimur.buckshooter.ui.gameprocess

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netimur.buckshooter.ui.gameprocess.event.GameProcessEvent
import com.netimur.buckshooter.ui.gameprocess.event.ShootBlankEvent
import com.netimur.buckshooter.ui.gameprocess.event.ShootCombatEvent
import com.netimur.buckshooter.ui.gameprocess.event.UsePhoneEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

internal class GameProcessViewModel : ViewModel() {
    private val blankCartridgesCountFlow = MutableStateFlow(0)
    private val combatCartridgesCountFlow = MutableStateFlow(0)
    val uiState: StateFlow<GameProcessUIState> = combine(
        blankCartridgesCountFlow,
        combatCartridgesCountFlow
    ) { blankCartridgesCount, combatCartridgesCount ->
        GameProcessUIState(
            cartridgesCount = blankCartridgesCount + combatCartridgesCount,
            blankCartridgesCount = blankCartridgesCount,
            combatCartridgesCount = combatCartridgesCount,
            isCartridgeSwapUsed = false
        )
    }.flowOn(Dispatchers.Default).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = GameProcessUIState.empty
    )

    fun handleEvent(event: GameProcessEvent) {
        when (event) {
            ShootBlankEvent -> shootBlank()
            ShootCombatEvent -> shootCombat()
            is UsePhoneEvent -> {}
        }
    }

    private fun shootBlank() {
        blankCartridgesCountFlow.update {
            if (it > 0) {
                it - 1
            } else {
                it
            }
        }
    }

    private fun shootCombat() {
        combatCartridgesCountFlow.update {
            if (it > 0) {
                it - 1
            } else {
                it
            }
        }
    }


}