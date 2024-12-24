package com.gotneb.honki.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class BookResponseModel(
    val count: Int = 0,
    val next: String? = null,
    val previous: String? = null,
    val results: List<BookModel> = emptyList()
)