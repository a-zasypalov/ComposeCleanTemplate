package com.gaoyun.cct.data.network

import com.gaoyun.cct.data.model.UserDetailsResponse
import com.gaoyun.cct.data.model.UserResponse
import com.gaoyun.cct.data.network.ApiUtils.defaultHeaders
import com.gaoyun.cct.data.utils.requestAndCatch
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class UserRoutes @Inject constructor(
    private val apiClient: HttpClient
) {

    suspend fun getUsersPage(pageSize: Int, since: Int): List<UserResponse> {
        return apiClient.requestAndCatch {
            get("${ApiUtils.ENDPOINT_URL}/users") {
                defaultHeaders()
            }
        }
    }

    suspend fun getUser(login: String): UserDetailsResponse {
        return apiClient.requestAndCatch {
            get("${ApiUtils.ENDPOINT_URL}/users/$login") {
                defaultHeaders()
            }
        }
    }

}