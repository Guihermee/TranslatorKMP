	package br.com.cerniauskas.translatorkmp.voice_to_text.presentation

import br.com.cerniauskas.translatorkmp.core.domain.util.toCommonStateFlow
import br.com.cerniauskas.translatorkmp.voice_to_text.domain.VoiceToTextParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VoiceToTextViewModel(
    private val parser: VoiceToTextParser,
    coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(VoiceToTextState())
    val state = _state.combine(parser.state) { state, voiceResult ->
        state.copy(
            spokenText = voiceResult.result,
            recordError = if (state.canRecord) {
                voiceResult.error
            } else {
                "Can't record without permission"
            },
            displayState = when {
                !state.canRecord || voiceResult.error != null -> DisplayState.Error
                voiceResult.result.isNotBlank() && !voiceResult.isSpeaking -> {
                    DisplayState.DisplayingResults
                }
                voiceResult.isSpeaking -> DisplayState.Speaking
                else -> DisplayState.WaitingToTalk
            }
        )
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), VoiceToTextState())
        .toCommonStateFlow()

    init {
        viewModelScope.launch {
            while (true) {
                if (state.value.displayState == DisplayState.Speaking) {
                    _state.update { it.copy(
                        powerRatios = it.powerRatios + parser.state.value.powerRatio
                    ) }
                }
                delay(50L)
            }
        }
    }

    fun onAction(action: VoiceToTextAction) {
        when (action) {
            is VoiceToTextAction.PermissionResult -> {
                _state.update { it.copy(canRecord = action.isGranted) }
            }
            VoiceToTextAction.Reset -> {
                parser.reset()
                _state.update { VoiceToTextState() }
            }
            is VoiceToTextAction.ToggleRecording -> toggleListening(action.languageCode)
            else -> Unit
        }
    }

    private fun toggleListening(languageCode: String) {
        _state.update { it.copy(powerRatios = emptyList()) }
        parser.cancel()
        if (state.value.displayState == DisplayState.Speaking) {
            parser.stopListening()
        } else {
            parser.startListening(languageCode)
        }
    }

}
