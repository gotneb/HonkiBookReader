package com.gotneb.honki.presentation.screen.home_screen

import com.gotneb.honki.domain.model.BookModel

data class HomeScreenState(
    val search: String = "",
    val isLoading: Boolean = false,
    val books: List<BookModel> = emptyList()
)
