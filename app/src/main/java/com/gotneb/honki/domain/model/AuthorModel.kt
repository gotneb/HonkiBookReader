package com.gotneb.honki.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthorModel(
    val birthYear: Int? = null,
    val deathYear: Int? = null,
    val name: String = ""
)