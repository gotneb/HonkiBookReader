package com.gotneb.honki.presentation.screen.reader_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.gotneb.honki.domain.repository.ApiRepository
import com.gotneb.honki.domain.util.ReaderScreenRoute
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import javax.inject.Inject

@HiltViewModel
class ReaderScreenViewModel @Inject constructor(
    private val repository: ApiRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val _state = MutableStateFlow(ReaderScreenState())
    val state = _state.asStateFlow()

    init {
        val args = savedStateHandle.toRoute<ReaderScreenRoute>()
        val htmlUrl = args.html

        viewModelScope.launch {
            htmlUrl?.let { url ->
                repository.fetchContent(url)
                    .onSuccess {
                        println("SUCCESS: fetch html")

                        _state.update {
                            it.copy(
                                content = processHtml(data),
                                isLoading = false,
                            )
                        }
                    }
                    .onFailure {
                        println("FAILURE: fetch html\n${message()}")
                        _state.update {
                            it.copy(isLoading = false)
                        }
                    }
                    .onError {
                        println("ERROR: fetch html\n${message()}")
                        _state.update {
                            it.copy(isLoading = false)
                        }
                    }
            }
        }
    }

    private fun processHtml(html: String): String {
        val preprocessedHtml = html
            .replace("<br>", "\n")
            .replace("<br/>", "\n")

        return Jsoup
            .parse(preprocessedHtml)
            .select("h1, h2, h3, h4, h5, h6, p")
            .joinToString("\n") { text ->
                // Extra space at the beginning of each tag
                "\n${text.text()}"
            }
    }
}