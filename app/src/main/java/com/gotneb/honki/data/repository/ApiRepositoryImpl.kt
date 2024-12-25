package com.gotneb.honki.data.repository

import com.gotneb.honki.domain.model.BookResponseModel
import com.gotneb.honki.domain.repository.ApiRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ktor.getApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode

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

    override suspend fun fetchContent(url: String): ApiResponse<String> {
        // I know this is kind weird '-'
        // But this api has weird ways to send data...
        url.replace(API.BASE_URL, "")
        return try {
            val response = httpClient.get(url)
            if (response.status == HttpStatusCode.Found) {
                // Extract the redirect URL from the "Location" header
                val redirectUrl = response
                    .headers[HttpHeaders.Location]
                    ?.replace("http://", "https://")
                println("Redirection: $redirectUrl")
                if (redirectUrl != null) {
                    // Make another request to the redirected URL
                    httpClient.getApiResponse(redirectUrl)
                } else {
                    ApiResponse.Failure.Error(Exception("Redirect location missing."))
                }
            } else {
                response.body<ApiResponse<String>>()
            }
        } catch (e: Exception) {
            ApiResponse.Failure.Error(e)
        }
    }
}