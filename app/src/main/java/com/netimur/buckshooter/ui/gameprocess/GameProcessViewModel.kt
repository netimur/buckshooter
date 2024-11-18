package com.netimur.buckshooter.ui.gameprocess

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netimur.buckshooter.data.model.CartridgeType
import com.netimur.buckshooter.data.repository.CartridgesRepository
import com.netimur.buckshooter.domain.usecases.ObserveBlankCartridgesUseCase
import com.netimur.buckshooter.domain.usecases.ObserveCombatCartridgesUseCase
import com.netimur.buckshooter.domain.usecases.ShootBlankCartridgeUseCase
import com.netimur.buckshooter.domain.usecases.ShootCombatCartridgeUseCase
import com.netimur.buckshooter.ui.gameprocess.event.GameProcessEvent
import com.netimur.buckshooter.ui.gameprocess.event.ShootBlankEvent
import com.netimur.buckshooter.ui.gameprocess.event.ShootCombatEvent
import com.netimur.buckshooter.ui.gameprocess.event.UsePhoneEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class GameProcessViewModel @Inject constructor(
    private val shootCombatCartridgeUseCase: ShootCombatCartridgeUseCase,
    private val shootBlankCartridgeUseCase: ShootBlankCartridgeUseCase,
    private val observeBlankCartridgesUseCase: ObserveBlankCartridgesUseCase,
    private val observeCombatCartridgesUseCase: ObserveCombatCartridgesUseCase,
    private val cartridgesRepository: CartridgesRepository
) : ViewModel() {
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

    init {
        observeCartridges()
    }

    private fun observeCartridges() {
        viewModelScope.launch {
            cartridgesRepository.observeCartridges().collect { cartridges ->
                blankCartridgesCountFlow.update {
                    cartridges.count { it.cartridgeType == CartridgeType.BLANK }
                }
                combatCartridgesCountFlow.update {
                    cartridges.count { it.cartridgeType == CartridgeType.COMBAT }
                }
            }
        }
    }

    fun handleEvent(event: GameProcessEvent) {
        when (event) {
            ShootBlankEvent -> shootBlank()
            ShootCombatEvent -> shootCombat()
            is UsePhoneEvent -> {}
        }
    }

    private fun shootBlank() {
        viewModelScope.launch {
            shootBlankCartridgeUseCase()
        }
    }

    private fun shootCombat() {
        viewModelScope.launch {
            shootCombatCartridgeUseCase()
        }
    }
}