package com.netimur.buckshooter.ui.gamesetting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netimur.buckshooter.data.model.Shell
import com.netimur.buckshooter.data.model.ShellOrdinalNumber
import com.netimur.buckshooter.data.model.ShellType
import com.netimur.buckshooter.domain.usecases.LoadShellsUseCase
import com.netimur.buckshooter.ui.gamesetting.event.AddBlankShellEvent
import com.netimur.buckshooter.ui.gamesetting.event.AddLiveShellEvent
import com.netimur.buckshooter.ui.gamesetting.event.ApplySettingEvent
import com.netimur.buckshooter.ui.gamesetting.event.GameSettingEvent
import com.netimur.buckshooter.ui.gamesetting.event.MinusBlankShellEvent
import com.netimur.buckshooter.ui.gamesetting.event.MinusLiveShellEvent
import com.netimur.buckshooter.ui.gamesetting.event.ResetBlankShellsCountEvent
import com.netimur.buckshooter.ui.gamesetting.event.ResetLiveShellsCountEvent
import com.netimur.buckshooter.ui.gamesetting.event.SelectBlankShellChipsEvent
import com.netimur.buckshooter.ui.gamesetting.event.SelectLiveShellChipsEvent
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
    private val loadShellsUseCase: LoadShellsUseCase
) : ViewModel() {
    private val liveShellsFlow = MutableStateFlow(0)
    private val blankShellsFlow = MutableStateFlow(0)
    private val isLiveCounterShakingFlow = MutableStateFlow(false)
    private val isBlankCounterShakingFlow = MutableStateFlow(false)

    private val _navigationEvent: MutableSharedFlow<OpenGameProcess> =
        MutableSharedFlow(extraBufferCapacity = 5)
    val navigationEvent = _navigationEvent.asSharedFlow()

    val uiState = combine(
        liveShellsFlow,
        blankShellsFlow,
        isLiveCounterShakingFlow,
        isBlankCounterShakingFlow
    ) { liveShells, blankShells, isLiveCounterShaking, isBlankCounterShaking ->
        GameSettingUIState(
            blankCount = blankShells,
            LiveCount = liveShells,
            isLiveCounterShaking = isLiveCounterShaking,
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
            AddBlankShellEvent -> addBlank()
            AddLiveShellEvent -> addLive()
            MinusBlankShellEvent -> minusBlank()
            MinusLiveShellEvent -> minusLive()
            is SelectBlankShellChipsEvent -> {
                setBlankShellsCount(count = event.selectedChips.value)
            }

            is SelectLiveShellChipsEvent -> {
                setLiveShellsCount(count = event.selectedChips.value)
            }

            ResetBlankShellsCountEvent -> {
                setBlankShellsCount(count = 0)
            }

            ResetLiveShellsCountEvent -> {
                setLiveShellsCount(count = 0)
            }

            ApplySettingEvent -> {
                viewModelScope.launch {
                    runCatching {
                        loadShellsUseCase(
                            shells = generateBlankShells() + generateLiveShells()
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

    private fun generateBlankShells(): List<Shell> {
        val blankShellsCount = blankShellsFlow.value
        return List(blankShellsCount) {
            Shell(
                orderNumber = ShellOrdinalNumber.Unknown,
                shellType = ShellType.BLANK
            )
        }
    }

    private fun generateLiveShells(): List<Shell> {
        val liveShellsCount = liveShellsFlow.value
        return List(liveShellsCount) {
            Shell(
                orderNumber = ShellOrdinalNumber.Unknown,
                shellType = ShellType.LIVE
            )
        }
    }

    private fun minusLive() {
        liveShellsFlow.update {
            if (it > 0) {
                it - 1
            } else {
                isLiveCounterShakingFlow.update { true }
                it
            }
        }
    }

    private fun minusBlank() {
        blankShellsFlow.update {
            if (it > 0) {
                it - 1
            } else {
                isBlankCounterShakingFlow.update { true }
                it
            }
        }
    }

    private fun addLive() {
        liveShellsFlow.update {
            it + 1
        }
    }

    private fun addBlank() {
        blankShellsFlow.update {
            it + 1
        }
    }

    private fun setBlankShellsCount(count: Int) {
        blankShellsFlow.update {
            count
        }
    }

    private fun setLiveShellsCount(count: Int) {
        liveShellsFlow.update {
            count
        }
    }

}