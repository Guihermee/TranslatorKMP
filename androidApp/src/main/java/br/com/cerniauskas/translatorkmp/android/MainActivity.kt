package br.com.cerniauskas.translatorkmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.cerniauskas.translatorkmp.android.translate.presentation.AndroidTranslateViewModel
import br.com.cerniauskas.translatorkmp.android.translate.presentation.TranslateScreen
import br.com.cerniauskas.translatorkmp.android.voice_to_text.presentation.AndroidVoiceToTextParserViewModel
import br.com.cerniauskas.translatorkmp.android.voice_to_text.presentation.VoiceToTextScreen
import br.com.cerniauskas.translatorkmp.android.core.presentation.Routes
import br.com.cerniauskas.translatorkmp.core.theme.TranslatorTheme
import br.com.cerniauskas.translatorkmp.translate.presentation.TranslateAction
import br.com.cerniauskas.translatorkmp.voice_to_text.presentation.VoiceToTextAction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TranslatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TranslateRoot()
                }
            }
        }
    }
}

@Composable
fun TranslateRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.Translate
    ) {
        composable<Routes.Translate> {
            val viewModel = hiltViewModel<AndroidTranslateViewModel>()
            val state by viewModel.state.collectAsState()

            val voiceResult by it
                .savedStateHandle
                .getStateFlow<String?>("voiceResult", null)
                .collectAsState()

            LaunchedEffect(voiceResult) {
                viewModel.onAction(TranslateAction.SubmitVoiceResult(voiceResult))
                it.savedStateHandle["voiceResult"] = null
            }

            TranslateScreen(
                state = state,
                onAction = { action ->
                    when (action) {
                        TranslateAction.RecordAudio -> {
                            navController.navigate(
                                Routes.VoiceToText(
                                    viewModel.state.value.fromLanguage.language.langCode
                                )
                            )
                        }

                        else -> viewModel.onAction(action)
                    }
                }
            )
        }
        composable<Routes.VoiceToText> {
            val languageCode = it.arguments?.getString("languageCode") ?: "en"
            val viewModel = hiltViewModel<AndroidVoiceToTextParserViewModel>()
            val state by viewModel.state.collectAsState()

            VoiceToTextScreen(
                state = state,
                languageCode = languageCode,
                onResult = { spokenText ->
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "voiceResult", spokenText
                    )
                    navController.popBackStack()
                },
                onAction = { action ->
                    when (action) {
                        is VoiceToTextAction.Close -> {
                            navController.popBackStack()
                        } else -> viewModel.onAction(action)
                    }
                }
            )
        }
    }
}