package com.gotneb.honki.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gotneb.honki.domain.util.HomeScreenRoute
import com.gotneb.honki.domain.util.ReaderScreenRoute
import com.gotneb.honki.presentation.screen.home_screen.HomeScreenViewModel
import com.gotneb.honki.presentation.screen.home_screen.HomeScreen
import com.gotneb.honki.presentation.screen.reader_screen.ReaderScreen
import com.gotneb.honki.presentation.screen.reader_screen.ReaderScreenViewModel
import com.gotneb.honki.presentation.ui.theme.HonkiBookReaderTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HonkiBookReaderTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = HomeScreenRoute
                ) {
                    composable<HomeScreenRoute> {
                        val viewModel = hiltViewModel<HomeScreenViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        HomeScreen(
                            state = state,
                            onBookClick = { bookHtmlUrl ->
                                navController.navigate(ReaderScreenRoute(bookHtmlUrl))
                            },
                            onValueChange = viewModel::onSearchChange,
                            onSearchClick = viewModel::searchBook,
                        )
                    }
                    composable<ReaderScreenRoute> {
                        val viewModel = hiltViewModel<ReaderScreenViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        ReaderScreen(state = state)
                    }
                }
            }
        }
    }
}