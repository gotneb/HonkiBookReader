package com.gotneb.honki.domain.repository

import com.gotneb.honki.domain.model.BookResponseModel
import com.skydoves.sandwich.ApiResponse


interface ApiRepository {
    suspend fun searchBook(title: String, language: String): ApiResponse<BookResponseModel>
}