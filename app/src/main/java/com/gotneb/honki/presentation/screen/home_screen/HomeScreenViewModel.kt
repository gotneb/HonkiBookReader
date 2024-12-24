package com.gotneb.honki.presentation.screen.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gotneb.honki.domain.repository.ApiRepository
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: ApiRepository
): ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()


    fun searchBook() {
        if (_state.value.search.length <= 3) {
            return
        }

        _state.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            repository
                .searchBook(
                    title = _state.value.search,
                    language = "en",
                )
                .onSuccess {
                    println("SUCCESS: Found books")

                    _state.update {
                        it.copy(
                            books = data.results,
                            isLoading = false,
                        )
                    }
                }
                .onFailure {
                    println("FAILURE: ${message()}")
                }
                .onError {
                    println("ERROR: ${message()}")
                }
        }
    }

    fun onSearchChange(text: String) {
        _state.update {
            it.copy(
                search = text
            )
        }
    }
}