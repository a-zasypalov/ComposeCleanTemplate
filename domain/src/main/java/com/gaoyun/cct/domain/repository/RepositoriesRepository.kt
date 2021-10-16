package com.gaoyun.cct.domain.repository

import com.gaoyun.cct.domain.model.Repository

interface RepositoriesRepository {
    suspend fun getRepositoriesPage(since: Int): List<Repository>
}