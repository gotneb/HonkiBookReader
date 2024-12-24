package com.gotneb.honki.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class BookModel(
    val id: Int,
    val title: String = "",
    val authors: List<AuthorModel> = emptyList(),
    val translators: List<TranslatorModel> = emptyList(),
    val languages: List<String> = emptyList(),
    val bookshelves: List<String> = emptyList(),
    val copyright: Boolean = false,
    val downloadCount: Int = 0,
    val formats: BookFormatsModel = BookFormatsModel(),
    val mediaType: String = "",
    val subjects: List<String> = emptyList(),
)