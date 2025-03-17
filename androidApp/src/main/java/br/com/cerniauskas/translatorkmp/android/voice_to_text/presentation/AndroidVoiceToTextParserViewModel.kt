package br.com.cerniauskas.translatorkmp.android.voice_to_text.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.cerniauskas.translatorkmp.android.voice_to_text.data.AndroidVoiceToTextParser
import br.com.cerniauskas.translatorkmp.voice_to_text.domain.VoiceToTextParser
import br.com.cerniauskas.translatorkmp.voice_to_text.presentation.VoiceToTextAction
import br.com.cerniauskas.translatorkmp.voice_to_text.presentation.VoiceToTextViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidVoiceToTextParserViewModel @Inject constructor(
    private val parser: VoiceToTextParser
): ViewModel() {

    private val viewModel by lazy {
        VoiceToTextViewModel(
            parser = parser,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onAction(action: VoiceToTextAction) {
        viewModel.onAction(action)
    }

}