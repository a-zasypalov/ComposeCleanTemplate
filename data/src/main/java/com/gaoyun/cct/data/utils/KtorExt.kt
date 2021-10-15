package com.gaoyun.cct.data.utils

import com.gaoyun.cct.data.network.ApiUtils.handleDefaultApiErrors
import io.ktor.client.*

suspend fun <T> HttpClient.requestAndCatch(
    block: suspend HttpClient.() -> T,
    error: suspend Throwable.() -> T,
): T = runCatching { block() }.getOrElse { error(it) }

suspend fun <T> HttpClient.requestAndCatch(
    block: suspend HttpClient.() -> T,
): T = requestAndCatch(
    {
        block()
    },
    {
        printStackTrace()
        handleDefaultApiErrors()
    }
)