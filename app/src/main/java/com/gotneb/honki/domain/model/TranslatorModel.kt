package com.gotneb.honki.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TranslatorModel(
    val birthYear: Int? = null,
    val deathYear: Int? = null,
    val name: String = ""
)