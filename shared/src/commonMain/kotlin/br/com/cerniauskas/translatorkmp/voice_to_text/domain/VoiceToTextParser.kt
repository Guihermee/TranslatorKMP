package br.com.cerniauskas.translatorkmp.voice_to_text.domain

import br.com.cerniauskas.translatorkmp.core.domain.util.CommonStateFlow

interface VoiceToTextParser {
    val state: CommonStateFlow<VoiceToTextParserState>
    fun startListening(langCode: String)
    fun stopListening()
    fun cancel()
    fun reset()
}