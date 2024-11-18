package com.netimur.buckshooter.ui.gameprocess

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netimur.buckshooter.data.model.ShellType
import com.netimur.buckshooter.data.repository.ShellsRepository
import com.netimur.buckshooter.domain.usecases.ShootBlankShellUseCase
import com.netimur.buckshooter.domain.usecases.ShootLiveShellUseCase
import com.netimur.buckshooter.ui.gameprocess.event.BurnerPhoneClickEvent
import com.netimur.buckshooter.ui.gameprocess.event.CloseBurnerPhoneEvent
import com.netimur.buckshooter.ui.gameprocess.event.GameProcessEvent
import com.netimur.buckshooter.ui.gameprocess.event.ResetBurnerPhoneOrderNumberEvent
import com.netimur.buckshooter.ui.gameprocess.event.SelectBurnerPhoneShellOrderEvent
import com.netimur.buckshooter.ui.gameprocess.event.SelectBurnerPhoneShellTypeEvent
import com.netimur.buckshooter.ui.gameprocess.event.ShootBlankEvent
import com.netimur.buckshooter.ui.gameprocess.event.ShootLiveEvent
import com.netimur.buckshooter.ui.gameprocess.event.UsePhoneEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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
    private val shootLiveShellUseCase: ShootLiveShellUseCase,
    private val shootBlankShellUseCase: ShootBlankShellUseCase,
    private val shellsRepository: ShellsRepository
) : ViewModel() {
    private val blankShellsCountFlow = MutableStateFlow(0)
    private val liveShellsCountFlow = MutableStateFlow(0)
    private val burnerPhoneStateFlow = MutableStateFlow(
        BurnerPhoneState(
            isExpanded = false,
            selectedOrderNumber = null,
            selectedShellType = null
        )
    )
    val uiState: StateFlow<GameProcessUIState> = combine(
        blankShellsCountFlow,
        liveShellsCountFlow,
        burnerPhoneStateFlow
    ) { blankShellsCount, liveShellsCount, burnerPhoneState ->
        GameProcessUIState(
            shellsCount = blankShellsCount + liveShellsCount,
            blankShellsCount = blankShellsCount,
            liveShellsCount = liveShellsCount,
            isInverterEnabled = false,
            burnerPhoneState = burnerPhoneState
        )
    }.flowOn(Dispatchers.Default).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = GameProcessUIState.empty
    )

    private var observeShellsJob: Job? = null

    init {
        observeShells()
    }

    private fun observeShells() {
        observeShellsJob?.cancel()
        observeShellsJob = viewModelScope.launch {
            shellsRepository.observeShells().collect { shells ->
                blankShellsCountFlow.update {
                    shells.count { it.shellType == ShellType.BLANK }
                }
                liveShellsCountFlow.update {
                    shells.count { it.shellType == ShellType.LIVE }
                }
            }
        }
    }

    fun handleEvent(event: GameProcessEvent) {
        when (event) {
            ShootBlankEvent -> shootBlank()
            ShootLiveEvent -> shootLive()
            is UsePhoneEvent -> {}
            BurnerPhoneClickEvent -> {
                burnerPhoneStateFlow.update {
                    it.copy(
                        isExpanded = true
                    )
                }
            }

            CloseBurnerPhoneEvent -> {
                burnerPhoneStateFlow.update {
                    it.copy(
                        isExpanded = false
                    )
                }
            }

            ResetBurnerPhoneOrderNumberEvent -> {}
            is SelectBurnerPhoneShellOrderEvent -> {}
            is SelectBurnerPhoneShellTypeEvent -> {}
        }
    }

    private fun shootBlank() {
        viewModelScope.launch {
            shootBlankShellUseCase()
            observeShells()
        }
    }

    private fun shootLive() {
        viewModelScope.launch {
            shootLiveShellUseCase()
            observeShells()
        }
    }
}