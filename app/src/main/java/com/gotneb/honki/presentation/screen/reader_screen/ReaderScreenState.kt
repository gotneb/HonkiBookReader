package com.gotneb.honki.presentation.screen.reader_screen

data class ReaderScreenState(
    val content: List<StyledText> = emptyList(),
    val isLoading: Boolean = true,
)
