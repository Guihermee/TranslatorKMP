package br.com.cerniauskas.translatorkmp.translate.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.cerniauskas.translatorkmp.translate.domain.HistoryDataSource
import br.com.cerniauskas.translatorkmp.translate.domain.usercase.Translate

class AndroidTranslateViewModel(
    private val translate: Translate,
    private val historyDataSource: HistoryDataSource
): ViewModel() {
    private val viewModel by lazy {
        TranslateViewModel(
            translate = translate,
            historyDataSource = historyDataSource,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onAction(action: TranslateAction) {
        viewModel.onAction(action)
    }

}