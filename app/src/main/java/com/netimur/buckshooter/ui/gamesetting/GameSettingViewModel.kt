package com.netimur.buckshooter.ui.gamesetting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netimur.buckshooter.data.model.Cartridge
import com.netimur.buckshooter.data.model.CartridgeOrdinalNumber
import com.netimur.buckshooter.data.model.CartridgeType
import com.netimur.buckshooter.domain.usecases.LoadCartridgesUseCase
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
import com.netimur.buckshooter.ui.gamesetting.navigation.OpenGameProcess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class GameSettingViewModel @Inject constructor(
    private val loadCartridgesUseCase: LoadCartridgesUseCase
) : ViewModel() {
    private val combatCartridgesFlow = MutableStateFlow(0)
    private val blankCartridgesFlow = MutableStateFlow(0)
    private val isCombatCounterShakingFlow = MutableStateFlow(false)
    private val isBlankCounterShakingFlow = MutableStateFlow(false)

    private val _navigationEvent: MutableSharedFlow<OpenGameProcess> =
        MutableSharedFlow(extraBufferCapacity = 5)
    val navigationEvent = _navigationEvent.asSharedFlow()

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
                viewModelScope.launch {
                    runCatching {
                        loadCartridgesUseCase(
                            cartridges = generateBlankCartridges() + generateCombatCartridges()
                        )
                    }.onFailure {
                        // TODO Add Error handling
                        Log.e("fuck y", null, it)
                    }.onSuccess {
                        _navigationEvent.tryEmit(OpenGameProcess)
                    }
                }
            }
        }
    }

    private fun generateBlankCartridges(): List<Cartridge> {
        val blankCartridgesCount = blankCartridgesFlow.value
        return List(blankCartridgesCount) {
            Cartridge(
                orderNumber = CartridgeOrdinalNumber.Unknown,
                cartridgeType = CartridgeType.BLANK
            )
        }
    }

    private fun generateCombatCartridges(): List<Cartridge> {
        val combatCartridgesCount = combatCartridgesFlow.value
        return List(combatCartridgesCount) {
            Cartridge(
                orderNumber = CartridgeOrdinalNumber.Unknown,
                cartridgeType = CartridgeType.COMBAT
            )
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