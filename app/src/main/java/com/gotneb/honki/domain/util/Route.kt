package com.gotneb.honki.domain.util

import kotlinx.serialization.Serializable

@Serializable
data object HomeScreenRoute

@Serializable
data class ReaderScreenRoute(val html: String?)