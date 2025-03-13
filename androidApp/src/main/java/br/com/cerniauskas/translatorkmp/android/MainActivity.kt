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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.cerniauskas.translatorkmp.android.translate.presentation.TranslateScreen
import br.com.cerniauskas.translatorkmp.core.presentation.Routes
import br.com.cerniauskas.translatorkmp.core.theme.TranslatorTheme
import br.com.cerniauskas.translatorkmp.translate.presentation.AndroidTranslateViewModel
import org.koin.androidx.compose.koinViewModel

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
            val viewModel = koinViewModel<AndroidTranslateViewModel>()
            val state by viewModel.state.collectAsState()
            TranslateScreen(
                state = state,
                onAction = viewModel::onAction
            )
        }
    }
}