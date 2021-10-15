package com.gaoyun.cct.data.network

import io.ktor.client.request.*
import io.ktor.http.*

object ApiUtils {

    const val ENDPOINT_URL = "https://api.github.com"

    const val PAGE_SIZE_PARAM = "per_page"
    const val LAST_ID_PARAM = "since"

    fun HttpRequestBuilder.defaultHeaders() = headers {
        append(HttpHeaders.ContentType, "application/json")
    }

    fun Throwable.handleDefaultApiErrors(): Nothing = throw this
//        if (this is ResponseException) {
//            when (this.response.status) {
//            }
//        } else {
//            throw
//        }

}