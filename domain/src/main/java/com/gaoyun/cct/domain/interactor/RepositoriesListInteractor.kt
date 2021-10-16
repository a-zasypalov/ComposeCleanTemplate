package com.gaoyun.cct.domain.interactor

import com.gaoyun.cct.domain.repository.RepositoriesRepository
import com.gaoyun.cct.domain.repository.UserRepository
import javax.inject.Inject

class RepositoriesListInteractor @Inject constructor(
    private val repository: RepositoriesRepository
) {

    suspend fun getRepositoriesPage(since: Int) = repository.getRepositoriesPage(since)

}