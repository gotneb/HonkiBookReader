package com.gotneb.honki.data.repository

import com.gotneb.honki.domain.model.BookResponseModel
import com.gotneb.honki.domain.repository.ApiRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ktor.getApiResponse
import io.ktor.client.HttpClient

object API {
    const val BASE_URL = "https://gutendex.com/"
}

class ApiRepositoryImpl(
    val httpClient: HttpClient
): ApiRepository {
    override suspend fun searchBook(title: String, language: String): ApiResponse<BookResponseModel> =
        httpClient
            .getApiResponse("books") {
                url {
                    parameters.append("search", title)
                    parameters.append("languages", language)
                }
            }
}