package com.netimur.buckshooter.ui.gamesetting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netimur.buckshooter.ui.gamesetting.event.AddBlankCartridgeEvent
import com.netimur.buckshooter.ui.gamesetting.event.AddCombatCartridgeEvent
import com.netimur.buckshooter.ui.gamesetting.event.ApplySettingEvent
import com.netimur.buckshooter.ui.gamesetting.event.GameSettingEvent
import com.netimur.buckshooter.ui.gamesetting.event.MinusBlankCartridgeEvent
import com.netimur.buckshooter.ui.gamesetting.event.MinusCombatCartridgeEvent
import com.netimur.buckshooter.ui.gamesetting.event.ResetBlankCartridgesCountEvent
import com.netimur.buckshooter.ui.gamesetting.event.ResetCombatCartridgesCountEvent
import com.netimur.buckshooter.ui.gamesetting.event.SelectBlankCartridgeChipsEvent
import com.netimur.buckshooter.ui.gamesetting.event.SelectCombatCartridgeChipsEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

internal class GameSettingViewModel : ViewModel() {
    private val combatCartridgesFlow = MutableStateFlow(0)
    private val blankCartridgesFlow = MutableStateFlow(0)
    private val isCombatCounterShakingFlow = MutableStateFlow(false)
    private val isBlankCounterShakingFlow = MutableStateFlow(false)

    val uiState = combine(
        combatCartridgesFlow,
        blankCartridgesFlow,
        isCombatCounterShakingFlow,
        isBlankCounterShakingFlow
    ) { combatCartridges, blankCartridges, isCombatCounterShaking, isBlankCounterShaking ->
        GameSettingUIState(
            blankCount = blankCartridges,
            combatCount = combatCartridges,
            isCombatCounterShaking = isCombatCounterShaking,
            isBlankCounterShaking = isBlankCounterShaking
        )
    }.flowOn(
        context = Dispatchers.Default
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = GameSettingUIState.empty
    )

    fun handleEvent(event: GameSettingEvent) {
        when (event) {
            AddBlankCartridgeEvent -> addBlank()
            AddCombatCartridgeEvent -> addCombat()
            MinusBlankCartridgeEvent -> minusBlank()
            MinusCombatCartridgeEvent -> minusCombat()
            is SelectBlankCartridgeChipsEvent -> {
                setBlankCartridgesCount(count = event.selectedChips.value)
            }

            is SelectCombatCartridgeChipsEvent -> {
                setCombatCartridgesCount(count = event.selectedChips.value)
            }

            ResetBlankCartridgesCountEvent -> {
                setBlankCartridgesCount(count = 0)
            }

            ResetCombatCartridgesCountEvent -> {
                setCombatCartridgesCount(count = 0)
            }

            ApplySettingEvent -> {
                // STORE Info to database
            }
        }
    }

    private fun minusCombat() {
        combatCartridgesFlow.update {
            if (it > 0) {
                it - 1
            } else {
                isCombatCounterShakingFlow.update { true }
                it
            }
        }
    }

    private fun minusBlank() {
        blankCartridgesFlow.update {
            if (it > 0) {
                it - 1
            } else {
                isBlankCounterShakingFlow.update { true }
                it
            }
        }
    }

    private fun addCombat() {
        combatCartridgesFlow.update {
            it + 1
        }
    }

    private fun addBlank() {
        blankCartridgesFlow.update {
            it + 1
        }
    }

    private fun setBlankCartridgesCount(count: Int) {
        blankCartridgesFlow.update {
            count
        }
    }

    private fun setCombatCartridgesCount(count: Int) {
        combatCartridgesFlow.update {
            count
        }
    }

}