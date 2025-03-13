package br.com.cerniauskas.translatorkmp.translate.presentation

import br.com.cerniauskas.translatorkmp.core.domain.onError
import br.com.cerniauskas.translatorkmp.core.domain.onSuccess
import br.com.cerniauskas.translatorkmp.core.domain.util.toCommonStateFlow
import br.com.cerniauskas.translatorkmp.core.presentation.UiLanguage
import br.com.cerniauskas.translatorkmp.translate.data.history.toHistoryEntity
import br.com.cerniauskas.translatorkmp.translate.domain.HistoryDataSource
import br.com.cerniauskas.translatorkmp.translate.domain.usercase.Translate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

class TranslateViewModel(
    private val translate: Translate,
    private val historyDataSource: HistoryDataSource,
    private val coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(TranslateState())
    val state = combine(
        _state,
        historyDataSource.getHistory()
    ) { state, history ->
        if (state.history != history) {
            state.copy(
                history = history.mapNotNull { item ->
                    UiHistoryItem(
                        id = item.id ?: return@mapNotNull null,
                        fromText = item.fromText,
                        toText = item.toText,
                        fromLanguage = UiLanguage.byCode(item.fromLanguageCode),
                        toLanguage = UiLanguage.byCode(item.toLanguageCode)
                    )
                }
            )
        } else state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), TranslateState())
        .toCommonStateFlow()

    private var translateJob: Job? = null

    fun onAction(action: TranslateAction) {
        when (action) {
            is TranslateAction.ChangeTranslationText -> {
                _state.update { it.copy(
                    fromText = action.text
                ) }
            }
            is TranslateAction.ChooseFromLanguage -> {
                _state.update { it.copy(
                    isChoosingFromLanguage = false,
                    fromLanguage = action.language
                ) }
            }
            is TranslateAction.ChooseToLanguage -> {
                val newState = _state.updateAndGet { it.copy(
                    isChoosingToLanguage = false,
                    toLanguage = action.language
                ) }
                translate(newState)
            }
            TranslateAction.CloseTranslation -> {
                _state.update { it.copy(
                    isTranslating = false,
                    fromText = "",
                    toText = null
                ) }
            }
            TranslateAction.EditTranslation -> {
                if (state.value.toText != null) {
                    _state.update { it.copy(
                        toText = null,
                        isTranslating = false
                    ) }
                }
            }
            TranslateAction.OnErrorSeen -> {
                _state.update { it.copy(
                    error = null
                ) }
            }
            TranslateAction.OpenFromLanguageDropDown -> {
                _state.update { it.copy(
                    isChoosingFromLanguage = true
                ) }
            }
            TranslateAction.OpenToLanguageDropDown -> {
                _state.update { it.copy(
                    isChoosingToLanguage = true
                ) }
            }
            is TranslateAction.SelectHistoryItem -> {
                translateJob?.cancel()
                _state.update { it.copy(
                    fromText = action.item.fromText,
                    toText = action.item.toText,
                    fromLanguage = action.item.fromLanguage,
                    toLanguage = action.item.toLanguage,
                    isTranslating = false
                ) }
            }
            TranslateAction.StopChoosingLanguage -> {
                _state.update { it.copy(
                    isChoosingFromLanguage = false,
                    isChoosingToLanguage = false
                ) }
            }
            is TranslateAction.SubmitVoiceResult -> {
                _state.update { it.copy(
                    fromText = action.result ?: it.fromText,
                    isTranslating = if (action.result != null) false else it.isTranslating,
                    toText = if (action.result != null) null else it.toText
                ) }
            }
            TranslateAction.SwapLanguages -> {
                _state.update { it.copy(
                    fromLanguage = it.toLanguage,
                    toLanguage = it.fromLanguage,
                    fromText = it.toText ?: "",
                    toText = if (it.toText != null) it.fromText else null
                )
                }
            }
            TranslateAction.Translate -> translate(state.value)
            else -> Unit
        }
    }

    private fun translate(state: TranslateState) {
        if (state.isTranslating || state.fromText.isBlank()) {
            return
        }

        translateJob = viewModelScope.launch {
            _state.update {
                it.copy(
                    isTranslating = true
                )
            }

            translate.invoke(
                fromLanguage = state.fromLanguage.language,
                fromText = state.fromText,
                toLanguage = state.toLanguage.language
            )
                .onSuccess { result ->
                    _state.update { it.copy(
                        isTranslating = false,
                        toText = result
                    ) }
                }
                .onError { error ->
                    _state.update { it.copy(
                        isTranslating = false,
                        error = error
                    ) }
                }
        }
    }
}
