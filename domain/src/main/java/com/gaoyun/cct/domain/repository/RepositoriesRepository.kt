package com.gaoyun.cct.domain.repository

import com.gaoyun.cct.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface RepositoriesRepository {
    suspend fun getRepositoriesPage(since: Int): Flow<List<Repository>>
    suspend fun getRepository(owner: String, name: String): Flow<Repository>
}