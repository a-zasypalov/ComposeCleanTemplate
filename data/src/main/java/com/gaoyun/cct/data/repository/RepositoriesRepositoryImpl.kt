package com.gaoyun.cct.data.repository

import com.gaoyun.cct.data.model.mapToRepository
import com.gaoyun.cct.data.network.RepositoryRoutes
import com.gaoyun.cct.domain.model.Repository
import com.gaoyun.cct.domain.repository.RepositoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoriesRepositoryImpl @Inject constructor(
    private val repositoryRoutes: RepositoryRoutes
) : RepositoriesRepository {

    override suspend fun getRepositoriesPage(since: Int): Flow<List<Repository>> = flow {
        emit(repositoryRoutes.getRepositoriesPage(since).map { it.mapToRepository() })
    }

    override suspend fun getRepository(owner: String, name: String): Flow<Repository> = flow {
        emit(repositoryRoutes.getRepository(owner, name).mapToRepository())
    }

}