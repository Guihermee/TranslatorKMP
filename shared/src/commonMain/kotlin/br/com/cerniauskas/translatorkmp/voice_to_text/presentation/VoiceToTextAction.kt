package br.com.cerniauskas.translatorkmp.voice_to_text.presentation

sealed class VoiceToTextAction {
    data object Close: VoiceToTextAction()
    data class PermissionResult(
        val isGranted: Boolean,
        val isPermanentlyDeclined: Boolean
    ): VoiceToTextAction()
    data class ToggleRecording(val languageCode: String): VoiceToTextAction()
    data object Reset: VoiceToTextAction()
}