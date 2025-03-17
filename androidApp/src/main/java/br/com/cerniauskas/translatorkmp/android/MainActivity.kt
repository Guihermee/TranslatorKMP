package br.com.cerniauskas.translatorkmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.cerniauskas.translatorkmp.android.translate.presentation.AndroidTranslateViewModel
import br.com.cerniauskas.translatorkmp.android.translate.presentation.TranslateScreen
import br.com.cerniauskas.translatorkmp.android.voice_to_text.presentation.VoiceToTextScreen
import br.com.cerniauskas.translatorkmp.core.presentation.Routes
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

            VoiceToTextScreen(
                languageCode = languageCode,
                onResult = {

                },
                onAction = { action ->
                    when (action) {
                        VoiceToTextAction.Close -> TODO()

                        else -> Unit
                    }
                }
            )
        }
    }
}