package com.gaoyun.cct.data.network

import com.gaoyun.cct.data.model.RepositoryResponse
import com.gaoyun.cct.data.model.UserDetailsResponse
import com.gaoyun.cct.data.model.UserResponse
import com.gaoyun.cct.data.network.ApiUtils.defaultHeaders
import com.gaoyun.cct.data.utils.requestAndCatch
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class RepositoryRoutes @Inject constructor(
    private val apiClient: HttpClient
) {

    suspend fun getRepositoriesPage(since: Int): List<RepositoryResponse> {
        return apiClient.requestAndCatch {
            get("${ApiUtils.ENDPOINT_URL}/repositories") {
                defaultHeaders()
            }
        }
    }

    suspend fun getRepository(owner: String, name: String): RepositoryResponse {
        return apiClient.requestAndCatch {
            get("${ApiUtils.ENDPOINT_URL}/repos/$owner/$name") {
                defaultHeaders()
            }
        }
    }

}