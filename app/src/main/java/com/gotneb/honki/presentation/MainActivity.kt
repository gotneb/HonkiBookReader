package com.gotneb.honki.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gotneb.honki.presentation.screen.home_screen.HomeScreenViewModel
import com.gotneb.honki.presentation.screen.home_screen.SearchScreen
import com.gotneb.honki.presentation.ui.theme.HonkiBookReaderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HonkiBookReaderTheme {
                val viewModel = hiltViewModel<HomeScreenViewModel>()
                val state = viewModel.state.collectAsStateWithLifecycle()

                SearchScreen(
                    state = state.value,
                    onValueChange = viewModel::onSearchChange,
                    onSearchCLick = viewModel::searchBook,
                )
            }
        }
    }
}